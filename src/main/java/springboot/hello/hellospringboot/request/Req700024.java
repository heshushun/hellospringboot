package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class Req700024 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * JMX名称
     */
    @NotBlank(message = "JMX名称不能为空")
    private String jmxName;
    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空")
    private String status;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户账号
     */
    private String userAccount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJmxName() {
        return jmxName;
    }

    public void setJmxName(String jmxName) {
        this.jmxName = jmxName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
