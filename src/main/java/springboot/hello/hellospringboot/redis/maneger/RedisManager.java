package springboot.hello.hellospringboot.redis.maneger;

import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import springboot.hello.hellospringboot.entity.UserEntity;

import java.util.List;

/**
 * redis 管理器
 * Created by hss on 2018/3/26.
 */
public class RedisManager {

    private RedisTemplate redisTemplate;

    //往Redis存入对象list;
    public <T> void initList(String key, List<T> tList){
        redisTemplate.delete(key);
        ListOperations listOperations = redisTemplate.opsForList();
        if (tList!=null && tList.size()>0) {
            listOperations.rightPushAll(key, tList);
        }
    }

    //从Redis里面取值list
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


    //往Redis存入单个对象;
    public void initObject(String key, Object value){
        redisTemplate.delete(key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    //从Redis里面取值 单个对象
    public Object getObject(String key){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
