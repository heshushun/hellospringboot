package springboot.hello.hellospringboot.task;

import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping("/quartz")
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
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    @RequestMapping(value = "/task/getJobInfo", method = RequestMethod.POST)
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }


    /**
     * 获取Job任务状态
     *
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public String getJobStatus(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        return scheduler.getTriggerState(triggerKey).name();
    }


    /**
     * 修改某个任务的执行时间
     *
     * @param name
     * @param group
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
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
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
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
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }


    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }


}
