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

    @TableField("t_name")
    private String name;

    @TableField("t_age")
    private int age;

    @TableField("t_address")
    private String address;

    public UserEntity() {
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
}
