package springboot.hello.hellospringboot.request;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 获取MD5加密后的签名 入参
 */
public class Req700012 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 机构代码
     */
    @NotBlank(message = "branchCode不能为空")
    private String branchCode;

    /**
     * 渠道
     */
    @NotBlank(message = "channel不能为空")
    private String channel;

    /**
     * 设备号
     */
    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    /**
     * 时间戳
     */
    @NotBlank(message = "timestamp不能为空")
    private String timestamp;

    /**
     * 密钥
     */
    @NotBlank(message = "secretKey不能为空")
    private String secretKey;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "Req700012{" +
                "branchCode='" + branchCode + '\'' +
                ", channel='" + channel + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
