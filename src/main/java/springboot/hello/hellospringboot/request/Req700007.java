package springboot.hello.hellospringboot.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 删除任务 的入参
 */
public class Req700007 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    @NotNull(message = "taskId不能为空")
    private Integer taskId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
