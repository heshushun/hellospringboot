package springboot.hello.hellospringboot.request;


import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加 用户 的入参
 */
public class Req700003 implements Serializable {
    private static final long serialVersionUID = -7323868806548711092L;

    @NotBlank(message = "名称不能为空")
    private String name;

    private int age;

    private String wechat;

    @NotBlank(message = "账号不能为空")
    private String account ;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String salt;

    private String userStatus;

    private Integer maxError;

    private String portrait;

    private String sex;

    private Integer departmentId;




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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
