package springboot.hello.hellospringboot.dao.destDao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
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

    // 查询列表
    public List<UserEntity> list();

    // 批量保存
    public void batchSave(List<UserEntity> userList);

    // 分页 查询列表
    public List<UserEntity> selectUserList(Pagination page, UserEntity userEntity);

    // 根据账号查用户
    public UserEntity selectByAccount(UserEntity user);
}
