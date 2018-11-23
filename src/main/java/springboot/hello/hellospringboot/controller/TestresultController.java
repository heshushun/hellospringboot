package springboot.hello.hellospringboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.common.utils.ExcelExportUtil;
import springboot.hello.hellospringboot.entity.Testresult;
import springboot.hello.hellospringboot.request.Req700015;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.TestresultService;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 测试结果表 前端控制器
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
@RestController
@RequestMapping("/testresult")
public class TestresultController {

    private  final static Logger logger = LoggerFactory.getLogger(TestresultController.class);

    @Autowired
    private TestresultService testresultService;

    /**
     * 获取时间戳作为分组
     * @return
     */
    @RequestMapping(value = "/getTs", method = RequestMethod.GET)
    public BaseResp get(){
        long ts = System.currentTimeMillis();
        return new BaseResp(Boolean.TRUE,Long.toString(ts));
    }


    /**
     * 导出测试结果信息
     * @return
     */
    @RequestMapping(value = "/exportTestResult",method = RequestMethod.POST)
    public BaseResp exportTestResult(HttpServletResponse response, Req700015 req){
        ExcelExportUtil<Testresult> excelExportUtil = new ExcelExportUtil<Testresult>(Testresult.class);
        List<Testresult> testresultList = null;
        String excelName = "testresultList";

        if(!StringUtils.isEmpty(req.getFileName())){
            excelName = req.getFileName();
        }
        try {
            Integer[] testresultCount = new Integer[3];
            testresultList = testresultService.list(req);
            if(req.getStatus()==null){
                testresultCount[0] = testresultList.size();
                req.setStatus("pass");
                testresultCount[1]  = testresultService.list(req).size();
                testresultCount[2] = testresultCount[0]-testresultCount[1];
            }else {
                testresultCount[0] = testresultList.size();
                testresultCount[1] = 0;
                testresultCount[2] = 0;
            }

            if((testresultList.size()!=0||testresultList!=null)&&testresultCount[0]!=null&&testresultCount[1]!=null) {

                // 告诉浏览器用什么软件可以打开此文件
                response.setHeader("content-Type", "application/vnd.ms-excel");
                // 下载文件的默认名称 浏览器会弹窗另存为
                response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode( excelName, "UTF-8") + ".xls");
                //编码
                response.setCharacterEncoding("UTF-8");

                // 基于注解导出
                excelExportUtil.exportAsAop2(excelName,"testresult.xls", testresultList, 5,testresultCount);
            }else {
                return new BaseResp<>(Boolean.FALSE,"导出测试结果表格无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>(Boolean.FALSE,"导出测试结果失败");
        }

        return new BaseResp<>(Boolean.TRUE,"导出测试结果成功");
    }

	
}
