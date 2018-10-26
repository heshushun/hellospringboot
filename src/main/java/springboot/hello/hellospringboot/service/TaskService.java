package springboot.hello.hellospringboot.service;

import springboot.hello.hellospringboot.entity.Task;
import com.baomidou.mybatisplus.service.IService;

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

}
