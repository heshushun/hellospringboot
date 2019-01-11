package springboot.hello.hellospringboot.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 删除 jmeter列表的入参
 */
public class Req700025 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    @NotNull(message = "id不能为空")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
