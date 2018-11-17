package springboot.hello.hellospringboot.common.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xuqw on 2018/4/4.
 */
public class BaseCache {
    @Autowired
    private CacheManager ehcacheManager;

    protected Cache getCache(String cacheName){
        return ehcacheManager.getCache(cacheName);
    }

}
