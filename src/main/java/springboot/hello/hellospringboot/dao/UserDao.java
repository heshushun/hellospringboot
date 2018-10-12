package springboot.hello.hellospringboot.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import springboot.hello.hellospringboot.entity.UserEntity;

import java.util.List;

/**
 * <p>
 * 用户DAO层
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
public interface UserDao extends BaseMapper<UserEntity> {
    public List<UserEntity> list();
}
