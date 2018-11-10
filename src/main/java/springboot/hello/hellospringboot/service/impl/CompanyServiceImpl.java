package springboot.hello.hellospringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.dao.yunDao.YunCompanyDao;
import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.dao.destDao.CompanyDao;
import springboot.hello.hellospringboot.service.CompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
