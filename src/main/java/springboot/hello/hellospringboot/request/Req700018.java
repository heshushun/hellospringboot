package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Req700018 implements Serializable {
    private static final long serialVersionUID = -7323868806548711092L;

    @NotNull(message = "ID不能为空")
    private Integer id;

    @NotBlank(message = "状态不能为空")
    private String userStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
