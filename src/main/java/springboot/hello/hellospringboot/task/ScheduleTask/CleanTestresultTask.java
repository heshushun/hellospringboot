package springboot.hello.hellospringboot.task.ScheduleTask;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import springboot.hello.hellospringboot.service.StockService;
import springboot.hello.hellospringboot.service.TestresultService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  多个定时任务  清空一天前的测试结果
 * </p>
 *
 * @author hss
 * @since 2018-11-30
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class CleanTestresultTask implements Job {

    private static final Logger log =  LoggerFactory.getLogger(CleanTestresultTask.class);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    // 用静态全局变量 防止 继承接口后 new 对象，其他注解变量值stockUrl等失效而获取不到值
    private static  CleanTestresultTask cleanTestresultTask;

    @Autowired
    private TestresultService testresultService;

    // 初始化 先装载类对象
    @PostConstruct
    public void init(){
        cleanTestresultTask = this;
    }

    private void before(String jobName){
        log.info(" ======》【"+jobName+"】  任务开始执行！！！");
    }

    private void after(String jobName){
        log.info(" ======》【"+jobName+"】  任务执行结束！！！");
    }

    // 需要执行的业务
    public void cleanTestresult(){
        cleanTestresultTask.testresultService.cleanTestresult();
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 获取定时任务的 执行方法和时间
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        CronTrigger cronTrigger = (CronTrigger) jobExecutionContext.getTrigger();
        String cron = cronTrigger.getCronExpression();

        log.info("=====》 这是【"+jobName+"】任务，"+"  当前时间: "+ df.format(new Date())+ "， cron: "+ cron );
        before(jobName.toString());
        cleanTestresult();
        after(jobName.toString());

    }
}
