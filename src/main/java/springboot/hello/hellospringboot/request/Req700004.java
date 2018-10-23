package springboot.hello.hellospringboot.request;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Req700004 implements Serializable {

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

