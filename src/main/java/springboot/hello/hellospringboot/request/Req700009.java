package springboot.hello.hellospringboot.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新 任务 的入参
 */
public class Req700009  implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 任务ID
     */
    @NotNull(message = "taskId不能为空")
    private Integer taskId;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名不能为空")
    private String taskName;
    /**
     * 任务执行的类
     */
    @NotBlank(message = "执行类不能为空")
    private String taskExeClass;
    /**
     * 任务的执行方法
     */
    @NotBlank(message = "执行方法不能为空")
    private String taskExeMethod;
    /**
     * 任务执行时间
     */
    @NotBlank(message = "执行时间不能为空")
    private String taskCron;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskExeClass() {
        return taskExeClass;
    }

    public void setTaskExeClass(String taskExeClass) {
        this.taskExeClass = taskExeClass;
    }

    public String getTaskExeMethod() {
        return taskExeMethod;
    }

    public void setTaskExeMethod(String taskExeMethod) {
        this.taskExeMethod = taskExeMethod;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
