package springboot.hello.hellospringboot.task;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import springboot.hello.hellospringboot.service.TaskService;

/**
 * <p>
 *  定时查库，并更新任务
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@Configuration
@EnableScheduling
@Component
public class ScheduleRefreshDatabase {

    @Autowired
    private TaskService taskService;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    // 每隔60s查库，并根据查询结果决定是否重新设置定时任务
    //@Scheduled(fixedRate = 1000 * 60000)
    @Scheduled(cron = "0 */1 *  * * *")
    protected void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
        String searchCron = taskService.getTaskCron(); // 从数据库查询出来的
        System.out.println("之前执行时间："+ currentCron);
        System.out.println("查询执行时间："+ searchCron);
        if (currentCron.equals(searchCron)) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
        } else {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
    }


}
