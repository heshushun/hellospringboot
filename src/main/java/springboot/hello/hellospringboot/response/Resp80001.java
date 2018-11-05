package springboot.hello.hellospringboot.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 造的 登录 用户信息
 */
public class Resp80001 {

    private List roles = new ArrayList();


    private String name = "超级管理员";

    private String avatar = "http://47.98.204.145/img/image/20180913/0f3a9811d172b91.png";

    private String introduction="认真负责";


    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
