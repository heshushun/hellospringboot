package springboot.hello.hellospringboot.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.Req700005;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    public String login(Req700005 res);

    public List<UserEntity> list();

    public void delUser(Integer id);

    public void addUser(UserEntity user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public void saveUserList(List<UserEntity> userList);

    public UserEntity getUserById(Integer id);

    public Page<UserEntity> selectUserPage(Page<UserEntity> page, UserEntity userEntity);

    public int updateStatus(UserEntity user);

    public int resetPassword(UserEntity user);

    // 根据账号查用户
    public UserEntity selectByAccount(UserEntity user);

    public int updatePass(UserEntity user);

    public List<UserEntity> friend(UserEntity user);

}
