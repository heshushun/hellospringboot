package springboot.hello.hellospringboot.dao;

import springboot.hello.hellospringboot.entity.Task;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.lang.String;
import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
public interface TaskDao extends BaseMapper<Task> {

    public String getTaskCron ();

    /**
     * 获取 状态为1的任务
     * @return
     */
    public List<Task> getOpenTaskList ();

}