package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@TableName("t_task")
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
	@TableId(value="task_id", type= IdType.AUTO)
	private Integer taskId;
    /**
     * 任务名称
     */
	@TableField("task_name")
	private String taskName;
    /**
     * 任务执行的类
     */
	@TableField("task_exe_class")
	private String taskExeClass;
    /**
     * 任务的执行方法
     */
	@TableField("task_exe_method")
	private String taskExeMethod;
    /**
     * 任务执行时间
     */
	@TableField("task_cron")
	private String taskCron;
    /**
     * 任务执行状态
     */
	@TableField("task_status")
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

	@Override
	protected Serializable pkVal() {
		return this.taskId;
	}

	@Override
	public String toString() {
		return "Task{" +
			"taskId=" + taskId +
			", taskName=" + taskName +
			", taskExeClass=" + taskExeClass +
			", taskExeMethod=" + taskExeMethod +
			", taskCron=" + taskCron +
			", taskStatus=" + taskStatus +
			"}";
	}
}
