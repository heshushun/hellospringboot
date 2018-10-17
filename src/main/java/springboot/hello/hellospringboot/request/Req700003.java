package springboot.hello.hellospringboot.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Req700003 implements Serializable {
    private static final long serialVersionUID = -7323868806548711092L;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private int age;

    @NotBlank(message = "地址不能为空")
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
        return "Req700003{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
