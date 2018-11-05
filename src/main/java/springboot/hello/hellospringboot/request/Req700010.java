package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新 任务 是否可用状态 的入参
 */
public class Req700010 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 任务ID
     */
    @NotNull(message = "taskId不能为空")
    private Integer taskId;

    /**
     * 任务执行状态
     */
    @NotBlank(message = "任务可用状态不能为空")
    private String taskStatus;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
