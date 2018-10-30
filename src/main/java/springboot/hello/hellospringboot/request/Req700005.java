package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户登录请求 入参
 */
public class Req700005 {

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

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
}
