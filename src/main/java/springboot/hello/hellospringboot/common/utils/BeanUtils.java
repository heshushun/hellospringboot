package springboot.hello.hellospringboot.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * bean 属性工具类 与 OrikaBeanMapper 用法类似
 * Created by xuebj on 2016/1/21.
 *
 * 使用示例：Class<T> clazz    BeanUtils.copyProperties(stock, clazz)
 */
public abstract class BeanUtils {

    /**
     * 在2个对象之间复制属性
     * @param sources                   源对象集合
     * @param clazz                     目标对象class对象
     * @param <T>                       目标对象类型
     * @return                          目标对象集合
     * @throws InstantiationException   复制属性错误
     * @throws IllegalAccessException   复制属性错误
     */
    public static <T,E> List<T> covert2List(List<E> sources, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        for (Object object: sources){
            result.add(copyProperties(object,clazz));
        }
        return result;
    }

    /**
     * 在2个对象之间复制属性
     * @param source                    源对象
     * @param clazz                     目标对象class对象
     * @param <T>                       目标对象类型
     * @return                          目标对象
     * @throws InstantiationException   复制属性错误
     * @throws IllegalAccessException   复制属性错误
     */
    public static <T> T copyProperties(Object source, Class<T> clazz){
        if (null == source){
            return null;
        }
        T instance;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException  e) {
            throw new RuntimeException("转换 属性错误");
        }catch (IllegalAccessException  e) {
            throw new RuntimeException("转换 属性错误");
        }
        copyProperties(source,instance,null,null);
        return instance;
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * @param source the source bean
     * @param target the target bean
     * @param editable the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)
            throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if(value instanceof String && StringUtils.isNotBlank((String)value)){//字符串类的把空转成null
                                value = StringUtils.trimToNull((String)value);
                            }
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}
