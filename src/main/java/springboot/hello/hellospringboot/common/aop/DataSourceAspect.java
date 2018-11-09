package springboot.hello.hellospringboot.common.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.multiple.DataSourceContextHolder;

/**
 * <p>
 * 数据源 切面类
 * </p>
 *
 * @author hss
 * @since 2018-11-08
 */
@Component // 把这个类交给spring管理
@Slf4j
@Aspect // 声明是切面
@Order(-1) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class DataSourceAspect {

    private static Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    // 注解进来的条件 （切入点）
    // within：用于匹配连接点所在的Java类或者包。也就是说，匹配 DataSource类下的所有方法。
    // annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标识的方法都将匹配。
    @Pointcut("@within(springboot.hello.hellospringboot.common.annotation.DataSource) || @annotation(springboot.hello.hellospringboot.common.annotation.DataSource)")
    public void pointCut(){

    }

    //达到条件后可进来（前置切面）
    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        log.info("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    //（后置切面）
    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}
