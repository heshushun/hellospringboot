package springboot.hello.hellospringboot.service;

import com.baomidou.mybatisplus.service.IService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import springboot.hello.hellospringboot.entity.UserEntity;

import java.util.List;

/**
 * <p>
 * 用户服务接口层
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
public  interface UserService extends IService<UserEntity> {

    public List<UserEntity> list();

    public void delUser(Integer id);

    public void addUser(UserEntity user);

    public void saveUserList(List<UserEntity> userList);

}
