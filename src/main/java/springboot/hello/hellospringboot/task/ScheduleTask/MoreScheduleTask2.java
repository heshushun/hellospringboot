package springboot.hello.hellospringboot.task.ScheduleTask;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  多个定时任务  任务类2
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class MoreScheduleTask2 implements Job {

    private static final Logger log =  LoggerFactory.getLogger(MoreScheduleTask1.class);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    private void before(String jobName){
        log.info("【"+jobName+"】  任务开始执行！！！");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 获取定时任务的 执行方法和时间
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        CronTrigger cronTrigger = (CronTrigger) jobExecutionContext.getTrigger();
        String cron = cronTrigger.getCronExpression();

        log.info("=====》 这是【"+jobName+"】任务，"+"  当前时间: "+ df.format(new Date())+ "， cron: "+ cron );
        before(jobName.toString());

        after(jobName.toString());
    }

    private void after(String jobName){
        log.info("【"+jobName+"】  任务执行结束！！！");
    }

}
