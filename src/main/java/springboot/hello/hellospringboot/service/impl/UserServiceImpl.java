package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import springboot.hello.hellospringboot.dao.UserDao;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.redis.maneger.RedisManager;
import springboot.hello.hellospringboot.redis.util.RedisConstant;
import springboot.hello.hellospringboot.service.UserService;

import java.util.List;

/**
 * <p>
 * 用户服务实现层
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    /**
     * 获取用户列表
     * @return
     */
    @Override
    //@Cacheable(value="users", key="#RedisConstant.user_list")
    @Cacheable(value="users")
    public List<UserEntity> list() {
        return this.baseMapper.list();
    }

    /**
     * 根据Id删除用户
     * condition 表示条件
     * @CacheEvict(value="users", condition="#id!=1")
     * @param id
     */
    @Override
    @CacheEvict(value="users",allEntries=true)
    public void delUser(Integer id) {
        this.baseMapper.deleteById(id);
        System.out.println("user删除");
    }

    /**
     * 保存单条用户
     * @param user
     */
    @Override
    @CacheEvict(value="users",allEntries=true)
    public void addUser(UserEntity user) {
        this.baseMapper.insert(user);
        System.out.println("添加user");
    }


}
