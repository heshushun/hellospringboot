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
import springboot.hello.hellospringboot.entity.Stockinfo;
import springboot.hello.hellospringboot.service.StockinfoService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;


/**
 * <p>
 *  多个定时任务  股票采集
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class StockinfoTask implements Job {

    private  static final Logger logger = LoggerFactory.getLogger(StockinfoTask.class);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    // 用静态全局变量 防止 继承接口后 new 对象，其他注解变量值stockinfoService等失效而获取不到值
    private static  StockinfoTask stockinfoTask;

    @Autowired
    private StockinfoService stockinfoService;

    // 初始化 先装载类对象
    @PostConstruct
    public void init(){
        stockinfoTask = this;
    }

    private void before(String jobName){
        logger.info(" ======》【"+jobName+"】  任务开始执行！！！");
    }
    private void after(String jobName){
        logger.info(" ======》【"+jobName+"】  任务执行结束！！！");
    }

    // 采集 执行方法
    public boolean selectStockinfo (){

        try{
            List<Stockinfo> stockinfoList = stockinfoTask.stockinfoService.selectYunStockinfoList();
            if(stockinfoList != null && stockinfoList.size()!= 0){

                logger.info("=============》从源库采集到的股票数为：："+stockinfoList.size());

                //先清空原有数据
                stockinfoTask.stockinfoService.deleteStocksInfo();
                //将查询到的数据 批量插入目的库中
                stockinfoTask.stockinfoService.insertBatchStocksInfo(stockinfoList);

                logger.info("============》股票信息已同步到目的库中");
            }else{
                logger.info("============》源库中没有数据，不需同步");
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取定时任务的 执行方法和时间
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        CronTrigger cronTrigger = (CronTrigger) jobExecutionContext.getTrigger();
        String cron = cronTrigger.getCronExpression();

        logger.info("=====》 这是【"+jobName+"】任务，"+"  当前时间: "+ df.format(new Date())+ "， cron: "+ cron );
        before(jobName.toString());
        selectStockinfo();// 采集股票
        after(jobName.toString());
    }
}
