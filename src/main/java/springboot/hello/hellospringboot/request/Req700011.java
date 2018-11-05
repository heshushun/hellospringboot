package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新 任务 是否启动状态 的入参
 */
public class Req700011 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 任务ID
     */
    @NotNull(message = "taskId不能为空")
    private Integer taskId;

    /**
     * 任务启动状态
     */
    @NotBlank(message = "任务是否启动状态不能为空")
    private String taskOnline;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskOnline() {
        return taskOnline;
    }

    public void setTaskOnline(String taskOnline) {
        this.taskOnline = taskOnline;
    }
}
