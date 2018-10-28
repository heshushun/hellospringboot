package springboot.hello.hellospringboot.task.ScheduleTaskController;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.hello.hellospringboot.entity.Task;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.TaskService;
import springboot.hello.hellospringboot.task.ScheduleTask.MoreScheduleTask1;
import springboot.hello.hellospringboot.task.SchedulerManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  多个定时任务 控制类。
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@RestController
@RequestMapping("/quartz")
public class MoreScheduleTaskController {

    @Resource(name = "multitaskScheduler")
    private Scheduler scheduler;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SchedulerManager schedulerManager;

    //@ResponseBody
    //@RequestMapping(value = "/task/{jobName}", method = RequestMethod.GET)
    //public Object task(@PathVariable(value = "jobName") String jobName) throws SchedulerException {

    /**
     * 定时任务调度初始化
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/task/init", method = RequestMethod.GET)
    public BaseResp<String> task() throws SchedulerException, ClassNotFoundException {

        // 先获取status为 1 的任务
        List<Task> taskList = taskService.getOpenTaskList();
        for (Task task :taskList) {

            if(schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1").equals("NORMAL")){
                continue;
            }

            if(schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1").equals("PAUSED")){
                schedulerManager.resumeJob(task.getTaskExeMethod(),"group1");
                continue;
            }

            Class StringToClass=Class.forName("springboot.hello.hellospringboot.task.ScheduleTask."+task.getTaskExeClass());
            System.err.println("StringToClass ======》 "+StringToClass);

            //配置定时任务对应的Job
            JobDetail jobDetail = JobBuilder
                    .newJob(StringToClass)
                    .usingJobData("jobName", task.getTaskExeMethod())
                    .withIdentity(task.getTaskExeMethod(),"group1")
                    .build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskCron());

            //构建触发器Trigger
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger_" + task.getTaskExeMethod(),"group1")
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
        return new BaseResp(Boolean.TRUE,"调度初始化成功！！！");
    }

    /**
     * 查询定时任务 在线状态
     * @param taskId
     * @return
     * @throws SchedulerException
     */
    @RequestMapping(value = "/task/getJobStatus", method = RequestMethod.GET)
    public BaseResp getJobStatus(Integer taskId) throws SchedulerException {
        Task task = taskService.getTaskById(taskId);
        String status = schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1");

        HashMap<String,Object> result = new HashMap<>();
        result.put("status",status);
        if (status.equals("NORMAL")){
            result.put("msg","定时任务已启动！");
        }else if(status.equals("PAUSED")){
            result.put("msg","定时任务已暂停！");
        }else if(status.equals("NONE")){
            result.put("msg","定时任务未启动！");
        }else {
            result.put("msg","其他！");
        }

        return new BaseResp(Boolean.TRUE,result);
    }

    /**
     * 暂停某个任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/task/pauseJob", method = RequestMethod.POST)
    public BaseResp pauseJob(Integer taskId)  {
        Task task = taskService.getTaskById(taskId);
        HashMap<String,Object> result = new HashMap<>();
        try {
            String status = schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1");
            if (status.equals("NORMAL")){
                schedulerManager.pauseJob(task.getTaskExeMethod(),"group1");
                result.put("status","success");
                result.put("msg","任务停止成功！");
            }else if(status.equals("PAUSED")){
                result.put("status","fail");
                result.put("msg","任务已是停止状态");
            }else if(status.equals("NONE")){
                result.put("status","fail");
                result.put("msg","任务未上线，不能停止！");
            }
        } catch (SchedulerException e) {
            result.put("status","fail");
            result.put("msg","停止失败！");
        }
        return new BaseResp(Boolean.TRUE,result);
    }

    /**
     * 恢复某个任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/task/resumeJob", method = RequestMethod.POST)
    public BaseResp resumeJob(Integer taskId)  {
        Task task = taskService.getTaskById(taskId);
        HashMap<String,Object> result = new HashMap<>();
        try {
            String status = schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1");
            if (status.equals("NORMAL")){
                result.put("status","fail");
                result.put("msg","任务已是启动状态");
            }else if(status.equals("PAUSED")){
                schedulerManager.resumeJob(task.getTaskExeMethod(),"group1");
                result.put("status","success");
                result.put("msg","任务恢复启动成功");
            }else if(status.equals("NONE")){
                result.put("status","fail");
                result.put("msg","任务未上线，不能恢复！");
            }
        } catch (SchedulerException e) {
            result.put("status","fail");
            result.put("msg","恢复失败！");
        }
        return new BaseResp(Boolean.TRUE,result);
    }


    /**
     * 下线某个任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/task/deleteJob", method = RequestMethod.POST)
    public BaseResp deleteJob(Integer taskId)  {
        Task task = taskService.getTaskById(taskId);
        HashMap<String,Object> result = new HashMap<>();
        try {
            String status = schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1");
            if (status.equals("NORMAL")||status.equals("PAUSED")){
                schedulerManager.deleteJob(task.getTaskExeMethod(),"group1");
                result.put("status","success");
                result.put("msg","任务下线成功！");
            }else if(status.equals("NONE")){
                result.put("status","fail");
                result.put("msg","任务本就是未上线！");
            }
        } catch (SchedulerException e) {
            result.put("status","fail");
            result.put("msg","任务下线失败！");
        }
        return new BaseResp(Boolean.TRUE,result);
    }


    /**
     * 获取所有的在线job
     * @return
     * @throws SchedulerException
     */
    @ResponseBody
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public BaseResp<String> Jobs() throws SchedulerException {
        //获取所有的job集合
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        return new BaseResp(Boolean.TRUE,jobKeys);
    }


    /**
     * 获取所有的trigger
     * @return
     * @throws SchedulerException
     */
    @ResponseBody
    @RequestMapping(value = "/triggers", method = RequestMethod.GET)
    public BaseResp<String> Triggers() throws SchedulerException {
        //获取所有的trigger集合
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        return new BaseResp(Boolean.TRUE,triggerKeys);
    }




}
