package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Task;

import java.io.Serializable;
import java.util.Map;

/**
 * 任务 分页的 请求
 */
public class Req700006 extends Page<Task> implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 搜索的内容
     */
    private String taskName;

    /**
     * 任务执行状态
     */
    private String taskStatus;

    /**
     * 任务启动状态
     */
    private String taskOnline;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskOnline() {
        return taskOnline;
    }

    public void setTaskOnline(String taskOnline) {
        this.taskOnline = taskOnline;
    }
}
