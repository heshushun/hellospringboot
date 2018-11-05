package springboot.hello.hellospringboot.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import springboot.hello.hellospringboot.entity.Task;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import springboot.hello.hellospringboot.request.Req700006;

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

    /**
     * 获取任务列表 （分页）
     * @return
     */
    public List<Task> selectTaskList(Pagination page, Task task);


}