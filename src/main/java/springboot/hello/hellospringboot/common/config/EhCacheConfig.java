package springboot.hello.hellospringboot.common.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

/**
 * 缓存 配置
 * Created by hss on 2018-11-16.
 */
@Configuration
@EnableCaching//启用缓存
public class EhCacheConfig {

    /**
     * @param ehcacheManager 是 net.sf.ehcache.CacheManager的一个实例
     * 配置EhCacheCacheManager缓存管理器
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager ehcacheManager) {
        return new EhCacheCacheManager(ehcacheManager);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() throws MalformedURLException {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.getObject();
        return factoryBean;

    }

}
