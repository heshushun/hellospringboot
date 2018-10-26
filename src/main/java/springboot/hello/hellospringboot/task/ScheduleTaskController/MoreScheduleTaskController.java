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

    @RequestMapping(value = "/task/init", method = RequestMethod.GET)
    public BaseResp<String> task() throws SchedulerException, ClassNotFoundException {

        // 先获取status为 1 的任务
        List<Task> taskList = taskService.getOpenTaskList();
        for (Task task :taskList) {

            if(schedulerManager.getJobStatus(task.getTaskExeMethod(),"group1").equals("")){
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
