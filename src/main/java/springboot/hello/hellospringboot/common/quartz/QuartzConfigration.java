package springboot.hello.hellospringboot.common.quartz;


import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import springboot.hello.hellospringboot.task.ScheduleTask.ScheduleTask;

/**
 * <p>
 *  Quartz 定时任务配置类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@Configuration
public class QuartzConfigration {

    /**
     * 单个定时任务使用 -
     * 配置定时任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
     *  是否并发执行
     *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
     *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
     */
        jobDetail.setConcurrent(false);
        jobDetail.setName("getHQ");            // 设置任务的名字
        jobDetail.setGroup("srd");             // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        jobDetail.setTargetObject(task);        //task为需要执行的实体类对应的对象
        jobDetail.setTargetMethod("saveStock");//saveStock为需要执行的方法
        return jobDetail;
    }

    /**
     * 单个定时任务 使用
     * 配置定时任务的触发器 
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression("0 30 20 * * ?"); // 初始时的cron表达式
        tigger.setName("getHQ");                    // trigger的name
        return tigger;
    }


    /**
     * 单个定时任务 使用
     * 定义《调度工厂scheduler》
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true); // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setStartupDelay(1);             // 延时启动，应用启动1秒后
        bean.setTriggers(cronJobTrigger);    // 注册触发器
        return bean;
    }


    /**
     * 多任务时的《调度工厂Scheduler》，动态设置Trigger。
     * 一个SchedulerFactoryBean可能会有多个Trigger
     */
    @Bean(name = "multitaskScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }

}

