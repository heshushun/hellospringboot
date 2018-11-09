package springboot.hello.hellospringboot.common.annotation;

import springboot.hello.hellospringboot.common.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * <p>
 * 用于aop类中当作切入点来选择数据源。注解标识
 * </p>
 *
 * @author hss
 * @since 2018-11-08
 */
@Target({ElementType.METHOD,ElementType.TYPE}) //Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上 等成分
@Retention(RetentionPolicy.RUNTIME)  //Retention注解决定 DataSource 注解的生命周期
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;
}
