package springboot.hello.hellospringboot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import springboot.hello.hellospringboot.common.annotation.ExceVo;

import java.io.Serializable;

/**
 * <p>
 * 用户实体层
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
@TableName("t_users")
public class UserEntity implements Serializable
{


    @TableId(value="t_id", type= IdType.AUTO)
    private Integer id;

    @ExceVo(sort = 2)
    @TableField("t_account")
    private String account;

    @ExceVo(sort = 1)
    @TableField("t_password")
    private String password;

    @ExceVo(sort = 3)
    @TableField("t_name")
    private String name;

    @ExceVo(sort = 4)
    @TableField("t_age")
    private int age;

    @ExceVo(sort = 5)
    @TableField("t_wechat")
    private String wechat;

    @ExceVo(sort = 6)
    @TableField("t_salt")
    private String salt;

    @ExceVo(sort = 7)
    @TableField("t_maxError")
    private Integer maxError;

    @ExceVo(sort = 8)
    @TableField("t_user_status")
    private String userStatus;

    @TableField("t_portrait")
    private String portrait;

    @TableField("t_sex")
    private String sex;

    @TableField("t_department_id")
    private Integer departmentId;


    public UserEntity() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getMaxError() {
        return maxError;
    }

    public void setMaxError(Integer maxError) {
        this.maxError = maxError;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wechat='" + wechat + '\'' +
                ", salt='" + salt + '\'' +
                ", maxError=" + maxError +
                ", userStatus='" + userStatus + '\'' +
                ", portrait='" + portrait + '\'' +
                ", sex='" + sex + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
