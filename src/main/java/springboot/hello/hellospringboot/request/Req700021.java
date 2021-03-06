package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * K 线行情比对 入参
 */
public class Req700021 implements Serializable {

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

    /**
     * 产品代码
     */
    // @NotBlank(message = "产品代码不能为空")
    private String prodCode;

    /**
     * K线类型
     */
    @NotNull(message = "K线类型不能为空")
    @Pattern(regexp = "1|2" ,message = "K线类型只能为1或2。1：分钟 2：日周月")
    private String type; // 1：代表分钟 2：代表日周月

    /**
     * 偏移天数
     */
    private Integer dayCount;

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

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }
}
