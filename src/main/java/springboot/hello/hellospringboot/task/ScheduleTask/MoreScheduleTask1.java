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
import springboot.hello.hellospringboot.common.utils.HttpUtil;
import springboot.hello.hellospringboot.entity.Stock;
import springboot.hello.hellospringboot.service.StockService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  多个定时任务  任务类1
 * </p>
 *
 * @author hss
 * @since 2018-10-26
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class MoreScheduleTask1 implements Job {

    private static final Logger log =  LoggerFactory.getLogger(MoreScheduleTask1.class);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式

    // 用静态全局变量 防止 继承接口后 new 对象，其他注解变量值stockUrl等失效而获取不到值
    private static  MoreScheduleTask1 moreScheduleTask1;

    @Autowired
    private StockService stockService;

    @Value("${stock.url}")
    private  String stockUrl;

    @Value("${stock.code}")
    private String stockCode;

    // 初始化 先装载类对象
   @PostConstruct
   public void init(){
       moreScheduleTask1 = this;
   }

    private void before(String jobName){
        log.info(" ======》【"+jobName+"】  任务开始执行！！！");
    }

    public void saveStock(){
        // 采集股票行情
        String[] stockList = moreScheduleTask1.stockCode.split(",");
        for (String stockCode : stockList) {
            String param = "list=" + stockCode;
            String response = HttpUtil.sendGet(moreScheduleTask1.stockUrl, param);
            log.info(" ======》 接口返回值：" + response);
            String stockHqValue = response.substring(response.indexOf("\"") + 1, response.lastIndexOf("\""));
            String[] stockHqList = stockHqValue.split(",");
            Stock stock = new Stock();
            stock.setStockCode(stockCode);
            stock.setStockName(stockHqList[0]);
            stock.setOpenPrice(Double.valueOf(stockHqList[1]));
            stock.setPreClosePrice(Double.valueOf(stockHqList[2]));
            stock.setCurPrice(Double.valueOf(stockHqList[3]));
            stock.setHighPrice(Double.valueOf(stockHqList[4]));
            stock.setLowPrice(Double.valueOf(stockHqList[5]));
            stock.setCompeteBuyPrice(Double.valueOf(stockHqList[6]));
            stock.setCompeteSellPrice(Double.valueOf(stockHqList[7]));
            stock.setVolume(Integer.valueOf(stockHqList[8]));
            stock.setTurnover(Double.valueOf(stockHqList[9]));
            stock.setBuyNum1(Integer.valueOf(stockHqList[10]));
            stock.setBuyPrice1(Double.valueOf(stockHqList[11]));
            stock.setBuyNum2(Integer.valueOf(stockHqList[12]));
            stock.setBuyPrice2(Double.valueOf(stockHqList[13]));
            stock.setBuyNum3(Integer.valueOf(stockHqList[14]));
            stock.setBuyPrice3(Double.valueOf(stockHqList[15]));
            stock.setBuyNum4(Integer.valueOf(stockHqList[16]));
            stock.setBuyPrice4(Double.valueOf(stockHqList[17]));
            stock.setBuyNum5(Integer.valueOf(stockHqList[18]));
            stock.setBuyPrice5(Double.valueOf(stockHqList[19]));
            stock.setSellNum1(Integer.valueOf(stockHqList[20]));
            stock.setSellPrice1(Double.valueOf(stockHqList[21]));
            stock.setSellNum2(Integer.valueOf(stockHqList[22]));
            stock.setSellPrice2(Double.valueOf(stockHqList[23]));
            stock.setSellNum3(Integer.valueOf(stockHqList[24]));
            stock.setSellPrice3(Double.valueOf(stockHqList[25]));
            stock.setSellNum4(Integer.valueOf(stockHqList[26]));
            stock.setSellPrice4(Double.valueOf(stockHqList[27]));
            stock.setSellNum5(Integer.valueOf(stockHqList[28]));
            stock.setSellPrice5(Double.valueOf(stockHqList[29]));
            stock.setDataDate(stockHqList[30]);
            stock.setDataTime(stockHqList[31]);
            moreScheduleTask1.stockService.addStock(stock);
        }
    }

    private void after(String jobName){
        log.info(" ======》【"+jobName+"】  任务执行结束！！！");
    }

    /**
     * 定时任务执行 execute
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 获取定时任务的 执行方法和时间
        Object jobName = jobExecutionContext.getJobDetail().getKey();
        CronTrigger cronTrigger = (CronTrigger) jobExecutionContext.getTrigger();
        String cron = cronTrigger.getCronExpression();

        log.info("=====》 这是【" + jobName + "】任务，" + "  当前时间: " + df.format(new Date()) + "， cron: " + cron);
        before(jobName.toString());
        saveStock();
        after(jobName.toString());
    }


}
