package springboot.hello.hellospringboot.task.ScheduleTask;

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

/**
 * <p>
 *  单个定时任务  任务类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@Configuration
@Component
@EnableScheduling // 任务注解
public class ScheduleTask {
    private static final Logger log =  LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private StockService stockService;

    @Value("${stock.url}")
    private String stockUrl;

    @Value("${stock.code}")
    private String stockCode;

    public void saveStock(){
        log.info("开始执行任务");
        String[] stockList = stockCode.split(",");
        for (String stockCode:stockList){
            String param = "list=" + stockCode ;
            String response = HttpUtil.sendGet(stockUrl , param);
            log.info("接口返回值："+response);
            String stockHqValue = response.substring(response.indexOf("\"")+1, response.lastIndexOf("\""));
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

            log.info("股票行情："+String.valueOf(stock));
            stockService.addStock(stock);
        }
        log.info("执行任务结束");
    }
}
