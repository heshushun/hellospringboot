package springboot.hello.hellospringboot.common.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;
import springboot.hello.hellospringboot.entity.Stockinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqw on 2018/4/4.
 */
@Component
public class StockCache extends BaseCache {
    private String cacheName = "stockCache";

    /**
     * 初始化缓存
     * @param stockList
     * @param key
     * @param <E>
     */
    public<E> void init(List<E> stockList ,String key){
        // 获取缓存
        Cache cache = this.getCache(cacheName);
        //删除缓存
        cache.remove(key);
        // 设置缓存
        cache.put(new Element(key, stockList));
    }

    /**
     * 从缓存中获取List
     * @param Key
     * @param <T>
     * @return
     */
    public <T>List<T> getList( String Key){
        Cache cache = this.getCache(cacheName);
        Element ele = cache.get(Key);
        if(ele == null){
            return new ArrayList<T>();
        }
        return (List<T>)ele.getObjectValue();
    }

    /**
     * 从缓存中根据 股票代码去获取 股票信息
     * @param stockCode
     * @return
     */
    public Stockinfo get(String stockCode){
        Cache cache = this.getCache(cacheName);
        Element ele = cache.get(CacheConstant.STOCK_LIST);
        if(ele == null){
            return null;
        }
        List<Stockinfo> list = (List<Stockinfo>)ele.getObjectValue();
        for(Stockinfo s : list){
            if(stockCode.equals(s.getStockCode())){
                return s;
            }
        }
        return null ;
    }

}
