package springboot.hello.hellospringboot.controller;


import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.common.utils.AccountUtil;
import springboot.hello.hellospringboot.common.utils.ExcelExportUtil;
import springboot.hello.hellospringboot.common.utils.ExcelUtil;
import springboot.hello.hellospringboot.entity.Task;
import springboot.hello.hellospringboot.entity.Testresult;
import springboot.hello.hellospringboot.request.Req700015;
import springboot.hello.hellospringboot.request.Req700016;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.TestresultService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;


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
     * 测试结果列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public BaseResp Page(HttpServletRequest request, Req700016 req710016){

        // 获取登录后的账号
        AccountUtil accountUtil = new AccountUtil();
        String account = accountUtil.getLoginAccount(request);
        req710016.setUserAccount(account);

        Page<Testresult> page = new Page<>();
        page.setCurrent(req710016.getCurrent());
        page.setSize(req710016.getSize());
        page.setAsc(false);
        page.setOrderByField("group1");

        Page<Testresult> testresultPage = testresultService.
                selectTestresultPage(page,orikaBeanMapper.map(req710016 ,Testresult.class));

        return new BaseResp(Boolean.TRUE,testresultPage);
    }

    /**
     * 项目列表
     *
     * @return
     */
    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    public BaseResp getProjectList(HttpServletRequest request){
        // 获取登录后的账号
        AccountUtil accountUtil = new AccountUtil();
        String userAccount = accountUtil.getLoginAccount(request);
        Testresult testresult = new  Testresult();
        testresult.setUserAccount(userAccount);

        List<String> projectList = testresultService.getProjectList(testresult);
        if(projectList.size()==0){
            return new BaseResp(Boolean.FALSE,projectList);
        }
        return new BaseResp(Boolean.TRUE,projectList);
    }

    /**
     * 测试结果列表
     *
     * @return
     */
    @RequestMapping(value = "/getTestresultList", method = RequestMethod.GET)
    public BaseResp getTestresultList(HttpServletRequest request, Req700015 req){
        // 获取登录后的账号
        AccountUtil accountUtil = new AccountUtil();
        String userAccount = accountUtil.getLoginAccount(request);
        req.setUserAccount(userAccount);

        List<Testresult> testresultList = testresultService.list(req);
        return new BaseResp(Boolean.TRUE,testresultList);
    }


    /**
     * 通过Id 获取单条测试结果
     * @param testresultId Id
     * @return
     */
    @RequestMapping(value = "/getTestresultById", method = RequestMethod.GET)
    public BaseResp getTestresultById(@RequestParam("testresultId") Integer testresultId) {
        Assert.notNull(testresultId,"Id不能为空");
        return new BaseResp(Boolean.TRUE,testresultService.getTestresultById(testresultId));
    }

    /**
     * 导出测试结果信息
     * @return
     */
    @RequestMapping(value = "/exportTestResult",method = RequestMethod.GET)
    public BaseResp exportTestResult(HttpServletRequest request, HttpServletResponse response,Req700015 req){
        ExcelExportUtil<Testresult> excelExportUtil = new ExcelExportUtil<Testresult>(Testresult.class);
        List<Testresult> testresultList = null;
        String excelName = "testresultList";

        AccountUtil accountUtil = new AccountUtil();
        String userAccount = accountUtil.getLoginAccount(request);
        req.setUserAccount(userAccount);

        FileOutputStream os= null;

        if(!StringUtils.isEmpty(req.getFileName())){
            excelName = req.getFileName();
        }
        try {
            Integer[] testresultCount = new Integer[3];
            testresultList = testresultService.list(req);
            if(req.getStatus()==null||req.getStatus().equals("")){
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

                // 设置 Content-Type
                response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
                // 设置 Content-Disposition
                response.setHeader("Content-Disposition", "attachment;filename*=" + URLEncoder.encode( excelName, "UTF-8") + ".xls");
                // 设置 编码
                response.setCharacterEncoding("UTF-8");

                // 基于注解导出
                HSSFWorkbook workbook = excelExportUtil.exportAsAop2(excelName,"testresult.xls", testresultList, 5,testresultCount);

                OutputStream out = new BufferedOutputStream(response.getOutputStream());
                workbook.write(out);
                out.flush();
                out.close();

                /*os = new FileOutputStream(new File("D:\\"+excelName+".xls"));
                workbook.write(os);
                os.close();*/

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
