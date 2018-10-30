package springboot.hello.hellospringboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springboot.hello.hellospringboot.common.interceptor.AccessTokenInterceptor;
import springboot.hello.hellospringboot.common.redis.maneger.RedisManager;
import springboot.hello.hellospringboot.common.redis.util.RedisConfigUtil;

/**
 * @Author: hss
 * @Date: 2018/10/30
 * @Desc: WebConfig
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * redis 管理器
     * @param redisTemplate
     * @return
     */
    /*@Bean
    public RedisManager redisManager(RedisTemplate redisTemplate){
        RedisManager redisManager = new RedisManager();
        redisManager.setRedisTemplate(redisTemplate);
        return redisManager;
    }*/

    /**
     * redisTemplate 序列化使用的jdkSerializeable,
     * 存储二进制字节码, 所以自定义序列化类
     * @param redisConnectionFactory
     * @return
     */
    /*@Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        return RedisConfigUtil.redisTemplate(redisConnectionFactory);
    }*/



    /**
     * 获取token过滤器
     * @return
     */
    @Bean
    public AccessTokenInterceptor accessTokenInterceptor() {
        return new AccessTokenInterceptor();
    }

    /**
     * 添加token 过滤器
     * @return
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 支持全局跨域
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET","OPTIONS")
                .maxAge(3600)
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }

}
