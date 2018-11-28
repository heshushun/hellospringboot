package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.common.exception.BizException;
import springboot.hello.hellospringboot.common.exception.builder.ErrorBuilder;
import springboot.hello.hellospringboot.common.helper.JwtHelper;
import springboot.hello.hellospringboot.common.utils.MD5Hash;
import springboot.hello.hellospringboot.dao.destDao.UserDao;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.Req700005;
import springboot.hello.hellospringboot.service.UserService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param res
     * @return
     */
    @Override
    //@DataSource(DataSourceEnum.DB1)
    public String login(Req700005 res) {

        //先从数据库获取用户
        UserEntity user = new UserEntity();
        user.setAccount(res.getAccount());
        UserEntity user1 = this.baseMapper.selectByAccount(user);

        //1、判断该用户是否存在
        if (user1 == null) {
            throw new BizException(ErrorBuilder.buildBizError("400", "你好，该账户不存在"));
        }
        //2、 先看用户是否可用
        String status = user1.getUserStatus();
        if (status.equals("0")) {
            throw new BizException(ErrorBuilder.buildBizError("400", "账户已被冻结,请联系管理员处理！"));
        }
        //3、验证密码是否正确
        try {
            if (StringUtils.isBlank(user1.getPassword()) || !MD5Hash.compare(user1.getPassword(), res.getPassword())) {
                Integer maxError = user1.getMaxError();
                maxError--;
                if (maxError > 0) {
                    user1.setMaxError(maxError);
                } else {
                    user1.setMaxError(maxError);
                    user1.setUserStatus("0");//冻结用户
                }
                userDao.updateById(user1);
                throw new BizException(ErrorBuilder.buildBizError("400", "账号密码错误,还剩" + maxError + "次机会"));
            }
        } catch (Exception e) {
            throw new BizException(ErrorBuilder.buildBizError(e.getMessage()));
        }
        user1.setMaxError(3);//验证密码成功 恢复默认3次
        userDao.updateById(user1);

        //4、生成token
        Map<String, String> map = new HashMap<>();
        map.put("id", user1.getId().toString());
        map.put("account", user1.getAccount());
        String jwt = JwtHelper.createJWT(JwtHelper.TIME_OUT, JwtHelper.SECRET, map);//生成token
        return jwt;
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    //@Cacheable(value="users" , key="#root.methodName")
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
    //@CacheEvict(value="users",allEntries=true)
    public void delUser(Integer id) {
        this.baseMapper.deleteById(id);
        System.out.println("user删除");
    }

    /**
     * 保存单条用户
     * @param user
     */
    @Override
    //@CacheEvict(value="users",allEntries=true)
    public void addUser(UserEntity user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password =  user.getPassword();
        //String salt = user.getSalt();
        MD5Hash hash = new MD5Hash(password,"hellospringboot",2);
        user.setPassword(hash.toString());
        user.setSalt("hellospringboot");
        user.setMaxError(3);
        user.setUserStatus("1");
        this.baseMapper.insert(user);
        System.out.println("添加user");
    }

    /**
     * 批量保存用户
     * @param userList
     */
    @Override
    //@CacheEvict(value="users",allEntries=true)
    public void saveUserList(List<UserEntity> userList){
        this.baseMapper.batchSave(userList);
        System.out.println("批量添加user");
    }

    /**
     * 根据Id 查询用户
     * @param id
     * @return
     */
    @Override
    //@DataSource(DataSourceEnum.DB1)
    public UserEntity getUserById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 分页查询用户
     * @param page, userEntity
     * @return
     */
    @Override
    //@Cacheable(value="users" , key="#root.methodName")
    public Page<UserEntity> selectUserPage(Page<UserEntity> page, UserEntity userEntity) {
        return page.setRecords(this.baseMapper.selectUserList(page,userEntity));
    }

    /**
     * 更新用户状态
     * @param user
     * @return
     */
    @Override
    public int updateStatus(UserEntity user) {
        return this.baseMapper.updateStatus(user);
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @Override
    public int resetPassword(UserEntity user) {
        String password =  "123123";
        MD5Hash hash = null;
        try {
            hash = new MD5Hash(password,"hellospringboot",2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(hash.toString());
        return this.baseMapper.resetPassword(user);
    }

}
