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
import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.entity.Stockinfo;
import springboot.hello.hellospringboot.service.CompanyService;
import springboot.hello.hellospringboot.service.StockinfoService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  多个定时任务  公司采集
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class CompanyTask implements Job{

    private static final Logger logger = LoggerFactory.getLogger(CompanyTask.class);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    // 用静态全局变量 防止 继承接口后 new 对象，其他注解变量值stockinfoService等失效而获取不到值
    private static CompanyTask companyTask;

    @PostConstruct
    public void init (){
        companyTask = this;
    }

    @Autowired
    private StockinfoService stockinfoService;

    @Autowired
    private CompanyService companyService;

    private void before(String jobName){
        logger.info(" ======》【"+jobName+"】  任务开始执行！！！");
    }
    private void after(String jobName){
        logger.info(" ======》【"+jobName+"】  任务执行结束！！！");
    }

    // 采集 执行方法
    public boolean  selectCompany (){

        try{
            Company company = null;
            Stockinfo stockinfo = null;
            List<Stockinfo> stockinfoList = companyTask.stockinfoService.stocklist(Stockinfo.class);// 从缓存中取股票

            for(int i= 0; i<stockinfoList.size();i++){
                stockinfo = stockinfoList.get(i);
                company = companyTask.companyService.getCompanyByStockCode(stockinfo.getStockCode());
                if (company == null) {
                    continue;
                }
                companyTask.companyService.deleteByStockCode(stockinfo.getStockCode());
                companyTask.companyService.insert(company);

                // 每隔100个股票打印一次日志
                if((i + 1) % 100 == 0){
                    logger.info("同步股票进度：" + (i+1) + "/" + stockinfoList.size());
                }
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
        selectCompany();// 采集公司
        after(jobName.toString());
    }
}
