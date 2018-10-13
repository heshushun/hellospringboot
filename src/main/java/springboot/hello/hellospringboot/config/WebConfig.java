package springboot.hello.hellospringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import springboot.hello.hellospringboot.redis.maneger.RedisManager;
import springboot.hello.hellospringboot.redis.util.RedisConfigUtil;

/**
 * Created by xuqw on 2018/2/24.
 */
//@Configuration
public class WebConfig {


    /**
     * redis 管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisManager redisManager(RedisTemplate redisTemplate){
        RedisManager redisManager = new RedisManager();
        redisManager.setRedisTemplate(redisTemplate);
        return redisManager;
    }

    /**
     * redisTemplate 序列化使用的jdkSerializeable,
     * 存储二进制字节码, 所以自定义序列化类
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        return RedisConfigUtil.redisTemplate(redisConnectionFactory);
    }

}
