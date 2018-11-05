package springboot.hello.hellospringboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.quartz.SchedulerException;
import springboot.hello.hellospringboot.entity.Task;
import com.baomidou.mybatisplus.service.IService;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.Req700006;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
public interface TaskService extends IService<Task> {

    public String getTaskCron ();

    /**
     * 获取 状态为1的任务
     * @return
     */
    public List<Task> getOpenTaskList ();

    /**
     * 根据 任务ID获取任务
     * @return
     */
    public Task getTaskById (Integer taskId);

    /**
     * 获取任务列表 （分页）
     * @return
     */
    public Page<Task> selectTaskPage(Page<Task> page, Task task);

    /**
     * 删除任务
     * @return
     */
    public void delTask(Integer taskId);

    /**
     * 添加任务
     * @return
     */
    public void addTask(Task task);

    /**
     * 启动任务
     * @return
     */
    public void startTask(Task task) throws SchedulerException, ClassNotFoundException;

    /**
     * 下线任务
     * @return
     */
    public String shutdownTask(Task task);

}
