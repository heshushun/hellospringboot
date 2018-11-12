package springboot.hello.hellospringboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Company;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
public interface CompanyService extends IService<Company> {

    // 根据股票代码删除 公司
    public int deleteByStockCode (String stockCode);

    // 根据 股票代码获取 公司
    public Company getCompanyByStockCode(String stockCode);

    //公司列表（分页）
    public Page<Company> selectCompanyPage(Page<Company> page, Company company);

    //获取行业列表
    public List<String> getIndustryList ();
}
