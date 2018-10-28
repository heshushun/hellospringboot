package springboot.hello.hellospringboot.task.ScheduleTask;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import springboot.hello.hellospringboot.service.StockService;

import javax.annotation.PostConstruct;
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

    private static final Logger log =  LoggerFactory.getLogger(MoreScheduleTask2.class);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    // 用静态全局变量 防止 继承接口后 new 对象，其他注解变量值stockUrl等失效而获取不到值
    private static  MoreScheduleTask2 moreScheduleTask2;

    @Autowired
    private StockService stockService;

    // 初始化 先装载类对象
    @PostConstruct
    public void init(){
        moreScheduleTask2 = this;
    }


    private void before(String jobName){
        log.info(" ======》【"+jobName+"】  任务开始执行！！！");
    }


    private void after(String jobName){
        log.info(" ======》【"+jobName+"】  任务执行结束！！！");
    }

    public void cleanStock(){
        moreScheduleTask2.stockService.cleanStock();
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 获取定时任务的 执行方法和时间
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        CronTrigger cronTrigger = (CronTrigger) jobExecutionContext.getTrigger();
        String cron = cronTrigger.getCronExpression();

        log.info("=====》 这是【"+jobName+"】任务，"+"  当前时间: "+ df.format(new Date())+ "， cron: "+ cron );
        before(jobName.toString());
        cleanStock();
        after(jobName.toString());
    }


}
