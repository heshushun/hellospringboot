package springboot.hello.hellospringboot.service.impl;

import springboot.hello.hellospringboot.entity.Task;
import springboot.hello.hellospringboot.dao.TaskDao;
import springboot.hello.hellospringboot.service.TaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {

    @Override
    public String getTaskCron() {
        return this.baseMapper.getTaskCron();
    }
}