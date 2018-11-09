package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.hello.hellospringboot.entity.Task;
import springboot.hello.hellospringboot.dao.destDao.TaskDao;
import springboot.hello.hellospringboot.service.TaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springboot.hello.hellospringboot.task.SchedulerManager;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource(name = "multitaskScheduler")
    private Scheduler scheduler;

    @Autowired
    private SchedulerManager schedulerManager;

    @Override
    public String getTaskCron() {
        return this.baseMapper.getTaskCron();
    }

    /**
     * 获取 状态为1的任务
     * @return
     */
    @Override
    public List<Task> getOpenTaskList() {
        return this.baseMapper.getOpenTaskList();
    }

    /**
     * 根据 任务ID获取任务
     * @return
     */
    @Override
    public Task getTaskById(Integer taskId) {
        return this.baseMapper.selectById(taskId);
    }

    /**
     * 获取任务列表 （分页）
     * @return
     */
    @Override
    public Page<Task> selectTaskPage(Page<Task> page, Task task) {
        return page.setRecords(this.baseMapper.selectTaskList(page,task));
    }

    /**
     * 删除任务
     * @return
     */
    @Override
    public void delTask(Integer taskId) {
        this.baseMapper.deleteById(taskId);
    }

    /**
     * 添加任务
     * @return
     */
    @Override
    public void addTask(Task task) {
        this.baseMapper.insert(task);
    }

    /**
     * 启动任务
     * @return
     */
    @Override
    public void startTask(Task task) throws SchedulerException, ClassNotFoundException {
        if(schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1").equals("NORMAL")){
        }else if(schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1").equals("PAUSED")){
            schedulerManager.resumeJob(task.getTaskExeMethod(),"group1");
        }else {
            Class StringToClass = Class.forName("springboot.hello.hellospringboot.task.ScheduleTask." + task.getTaskExeClass());
            System.err.println("StringToClass ======》 " + StringToClass);

            //配置定时任务对应的Job
            JobDetail jobDetail = JobBuilder
                    .newJob(StringToClass)
                    .usingJobData("jobName", task.getTaskExeMethod())
                    .withIdentity(task.getTaskExeMethod(), "group1")
                    .build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskCron());

            //构建触发器Trigger
            CronTrigger cronTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger_" + task.getTaskExeMethod(), "group1")
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    /**
     * 下线任务
     * @return
     */
    @Override
    public String  shutdownTask(Task task) {
        try {
            String status = schedulerManager.getJobStatus("trigger_" + task.getTaskExeMethod(),"group1");
            if (status.equals("NORMAL")||status.equals("PAUSED")){
                schedulerManager.deleteJob(task.getTaskExeMethod(),"group1");
            }else if(status.equals("NONE")){
                return "2"; // 没有在线，无法下线
            }
        } catch (SchedulerException e) {
            return "3"; // 下线失败
        }
        return "1"; // 下线成功
    }
}
