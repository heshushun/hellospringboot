package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 上海金 行情比对 入参
 */
public class Req700027 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 期望行情结果
     */
    @NotBlank(message = "期望行情结果不能为空")
    private String expectResult;

    /**
     * 实际行情结果
     */
    @NotBlank(message = "实际行情结果不能为空")
    private String realResult;


    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public String getRealResult() {
        return realResult;
    }

    public void setRealResult(String realResult) {
        this.realResult = realResult;
    }
}
