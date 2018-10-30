package springboot.hello.hellospringboot.request;


import org.hibernate.validator.constraints.NotBlank;

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

    @NotBlank(message = "账号不能为空")
    private String account ;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String salt;

    private Integer maxError;


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

}
