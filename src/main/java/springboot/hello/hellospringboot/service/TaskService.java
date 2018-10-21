package springboot.hello.hellospringboot.service;

import springboot.hello.hellospringboot.entity.Task;
import com.baomidou.mybatisplus.service.IService;

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

}
