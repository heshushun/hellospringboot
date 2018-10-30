package springboot.hello.hellospringboot.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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

    @TableField("t_account")
    private String account;

    @TableField("t_password")
    private String password;

    @TableField("t_name")
    private String name;

    @TableField("t_age")
    private int age;

    @TableField("t_address")
    private String address;

    @TableField("t_salt")
    private String salt;

    @TableField("t_maxError")
    private Integer maxError;

    @TableField("t_status")
    private Integer status;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
