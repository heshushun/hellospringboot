package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Testresult;

import java.io.Serializable;

/**
 * <p>
 * 测试结果 分页 传参
 * </p>
 *
 * @author hss
 * @since 2018-11-24
 */
public class Req700016 extends Page<Testresult> implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 分组
     */
    private String group1;
    /**
     * 项目名称
     */
    private String project;
    /**
     * 响应代码
     */
    private String responceCode;
    /**
     * 断言状态
     */
    private String status;
    /**
     * 用户账号
     */
    private String userAccount;


    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getResponceCode() {
        return responceCode;
    }

    public void setResponceCode(String responceCode) {
        this.responceCode = responceCode;
    }

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


}
