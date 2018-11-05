package springboot.hello.hellospringboot.controller;


import com.baomidou.mybatisplus.plugins.Page;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.entity.Task;
import springboot.hello.hellospringboot.entity.UserEntity;
import springboot.hello.hellospringboot.request.*;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.TaskService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private OrikaBeanMapper orikaBeanMapper;

    /**
     * 用户列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public BaseResp page( Req700006 req) {

        Page<Task> page = new Page<>();

        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        page.setOrderByField("task_id");
        page.setAsc(false);

        Page<Task> userPage = taskService.selectTaskPage(page,orikaBeanMapper.map(req,Task.class));

        return new BaseResp(Boolean.TRUE,userPage);
    }

    /**
     * 删除用户列表
     * @return
     */
    @RequestMapping(value = "/deleteTaskById" ,method = RequestMethod.POST)
    public BaseResp deleteById(@RequestBody @Valid Req700007 req) {
        taskService.delTask(req.getTaskId());
        return new BaseResp(Boolean.TRUE,"删除成功");
    }

    /**
     * 添加任务
     * @return
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public BaseResp save(@RequestBody @Valid Req700008 req) {
        req.setTaskOnline("0");
        taskService.addTask(orikaBeanMapper.map(req,Task.class));
        return new BaseResp(Boolean.TRUE,"新增成功");
    }

    /**
     * 更新任务
     * @param res
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseResp update(@Valid @RequestBody Req700009 res){
        Task task = orikaBeanMapper.map(res, Task.class);
        taskService.updateById(task);
        return new BaseResp<>(Boolean.TRUE,"更新成功");
    }

    /**
     * 通过Id获取任务
     * @param taskId 任务Id
     * @return
     */
    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public BaseResp<Task> getTaskById(@RequestParam("taskId") Integer taskId) {
        Assert.notNull(taskId,"任务Id不能为空");
        return new BaseResp<>(Boolean.TRUE,taskService.getTaskById(taskId));
    }

    /**
     * 更新任务 可用状态
     * @param res
     * @return
     */
    @RequestMapping(value = "/updateTaskStatus",method = RequestMethod.POST)
    public BaseResp updateTaskStatus(@Valid @RequestBody Req700010 res){
        Task curtask = taskService.getTaskById(res.getTaskId());
        if(curtask.getTaskOnline().equals('1')){
            return new BaseResp<>(Boolean.FALSE,"任务正在启动，不能直接关闭，需先下线任务");
        }
        Task task = orikaBeanMapper.map(res, Task.class);
        taskService.updateById(task);
        return new BaseResp<>(Boolean.TRUE,"更新状态成功");
    }

    /**
     * 更新任务 启动/下线状态
     * @param res
     * @return
     */
    @RequestMapping(value = "/updateTaskOnline",method = RequestMethod.POST)
    public BaseResp updateTaskOnline(@Valid @RequestBody Req700011 res){
        Task curtask = taskService.getTaskById(res.getTaskId());
        if(curtask.getTaskStatus().equals("0")){
            return new BaseResp<>(Boolean.FALSE,"非启用任务，无法 启动");
        }

        // 启动（上线）
        if(res.getTaskOnline().equals("1")){
            try {
                taskService.startTask(curtask);
            } catch (SchedulerException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{ //下线
            String result= taskService.shutdownTask(curtask);
            if (result.equals("2")){
                return new BaseResp<>(Boolean.FALSE,"本没有在线，无法下线");
            }else if (result.equals("3")){
                return new BaseResp<>(Boolean.FALSE,"下线失败");
            }
        }

        Task task = orikaBeanMapper.map(res, Task.class);
        taskService.updateById(task);
        return new BaseResp<>(Boolean.TRUE,"更新启动/停止状态 成功");
    }
	
}
