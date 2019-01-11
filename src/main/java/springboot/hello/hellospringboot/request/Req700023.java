package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Jmeter;
import springboot.hello.hellospringboot.entity.UserEntity;

import java.io.Serializable;

/**
 * jmeter脚本 查分页 （入参）
 */
public class Req700023 extends Page<Jmeter> implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    private String status;
    /**
     * 用户账号
     */
    private String userAccount;

    private String name;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
