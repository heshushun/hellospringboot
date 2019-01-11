package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 测试结果入库的 入参
 *
 */
public class Req700020 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目不能为空")
    private String project;
    /**
     * 结果分组
     */
    @NotBlank(message = "时间戳不能为空")
    private String group1;
    /**
     * 功能号
     */
    @NotBlank(message = "功能号不能为空")
    private String functionId;
    /**
     * 请求描述
     */
    @NotBlank(message = "请求描述不能为空")
    private String requestMsg;
    /**
     * 预期结果
     */
    @NotBlank(message = "预期结果不能为空")
    private String expectResult;
    /**
     * 响应结果
     */
    @NotBlank(message = "响应结果不能为空")
    private String responceResult;
    /**
     * 响应代码
     */
    @NotBlank(message = "响应码不能为空")
    private String responceCode;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空")
    private String userAccount;

    /**
     * 比对方式
     */
    @NotNull(message = "比对方式不能为空")
    private Integer compareType;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public String getResponceResult() {
        return responceResult;
    }

    public void setResponceResult(String responceResult) {
        this.responceResult = responceResult;
    }

    public String getResponceCode() {
        return responceCode;
    }

    public void setResponceCode(String responceCode) {
        this.responceCode = responceCode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getCompareType() {
        return compareType;
    }

    public void setCompareType(Integer compareType) {
        this.compareType = compareType;
    }
}
