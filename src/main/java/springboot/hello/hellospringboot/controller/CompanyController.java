package springboot.hello.hellospringboot.controller;


import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.common.orika.OrikaBeanMapper;
import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.request.Req700013;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.service.CompanyService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;

    /**
     * 公司列表（分页）
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public BaseResp Page(Req700013 req710013){

        Page<Company> page = new Page<>();
        page.setCurrent(req710013.getCurrent());
        page.setSize(req710013.getSize());
        page.setAsc(true);
        page.setOrderByField("stock_code");

        Page<Company> companyPage = companyService.
                selectCompanyPage(page,orikaBeanMapper.map(req710013 ,Company.class));

        return new BaseResp(Boolean.TRUE,companyPage);
    }

    /**
     * 行业列表
     *
     * @return
     */
    @RequestMapping(value = "/getIndustryList", method = RequestMethod.GET)
    public BaseResp getIndustryList(){
        List<String> industryList = companyService.getIndustryList();
        return new BaseResp(Boolean.TRUE,industryList);
    }
	
}
