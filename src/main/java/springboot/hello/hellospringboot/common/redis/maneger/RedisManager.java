package springboot.hello.hellospringboot.common.redis.maneger;

import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

/**
 * Created by hss on 2018/10/26.
 */
public class RedisManager {

    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> void initList(String key, List<T> tList){
        redisTemplate.delete(key);
        ListOperations listOperations = redisTemplate.opsForList();
        if (tList!=null && tList.size()>0) {
            listOperations.rightPushAll(key, tList);
        }
    }

    public <T> List<T> getList(String key, Class<T> clazz){
        BoundListOperations listOpt = redisTemplate.boundListOps(key);
        return (List<T>) listOpt.range(0, listOpt.size());
    }

    //往Redis存入字符串;
    public void initString(String key, String value){
        redisTemplate.delete(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }
    //从Redis里面取值
    public String getString(String key){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return (String)valueOperations.get(key);
    }
}
