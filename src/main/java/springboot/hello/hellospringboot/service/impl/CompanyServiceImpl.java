package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.dao.yunDao.YunCompanyDao;
import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.dao.destDao.CompanyDao;
import springboot.hello.hellospringboot.service.CompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company> implements CompanyService {

    @Autowired
    private YunCompanyDao yunCompanyDao;

    @Autowired
    private CompanyDao companyDao;

    // 根据股票代码删除 公司
    @Override
    public int deleteByStockCode(String stockCode) {
        return this.companyDao.deleteByStockCode(stockCode);
    }

    // 根据 股票代码获取 公司
    @Override
    @DataSource(DataSourceEnum.DB2)
    public Company getCompanyByStockCode(String stockCode) {
        return yunCompanyDao.getCompanyByStockCode(stockCode);
    }

    //公司列表（分页）
    @Override
    @DataSource(DataSourceEnum.DB1)
    public Page<Company> selectCompanyPage(Page<Company> page, Company company) {
        return page.setRecords(this.baseMapper.selectCompanyList(page,company));
    }

    //获取行业列表
    @Override
    public List<String> getIndustryList() {
        return this.baseMapper.getIndustryList();
    }

}
