package springboot.hello.hellospringboot.task;

import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 *  调度任务 管理器
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@Component
public class SchedulerManager {

    @Resource(name = "multitaskScheduler")
    private Scheduler scheduler;


    /**
     * 开始执行所有任务
     *
     * @throws SchedulerException
     */
    public void startJob() throws SchedulerException {
        //startJob1(scheduler);
        //startJob2(scheduler);
        scheduler.start();
    }


    /**
     * 获取Job信息
     *
     * @param trigger_name
     * @param trigger_group
     * @return
     * @throws SchedulerException
     */
    public String getJobInfo(String trigger_name, String trigger_group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(trigger_name, trigger_group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }


    /**
     * 获取Job任务状态
     *
     * @param trigger_name
     * @param trigger_group
     * @return
     * @throws SchedulerException
     */
    public String getJobStatus(String trigger_name, String trigger_group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(trigger_name, trigger_group);
        return scheduler.getTriggerState(triggerKey).name();
    }


    /**
     * 修改某个任务的执行时间
     *
     * @param trigger_name
     * @param trigger_group
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String trigger_name, String trigger_group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(trigger_name, trigger_group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(trigger_name, trigger_group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }


    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }


    /**
     * 暂停某个任务
     *
     * @param job_name
     * @param job_group
     * @throws SchedulerException
     */
    public void pauseJob(String job_name, String job_group) throws SchedulerException {
        JobKey jobKey = new JobKey(job_name, job_group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }


    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }


    /**
     * 恢复某个任务
     *
     * @param job_name
     * @param job_group
     * @throws SchedulerException
     */
    public void resumeJob(String job_name, String job_group) throws SchedulerException {
        JobKey jobKey = new JobKey(job_name, job_group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }


    /**
     * 删除某个任务
     *
     * @param job_name
     * @param job_group
     * @throws SchedulerException
     */
    public void deleteJob(String job_name, String job_group) throws SchedulerException {
        JobKey jobKey = new JobKey(job_name, job_group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }


}
