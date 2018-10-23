package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.plugins.Page;

import springboot.hello.hellospringboot.entity.UserEntity;


import java.io.Serializable;

/**
 * 用户 分页的 请求
 */
public class Req700001 extends Page<UserEntity> implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    // @NotBlank(message = "名称不能为空")
    private String name;

    private int age;

    private String address;

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

    @Override
    public String toString() {
        return "Req700001{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
