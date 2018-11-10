package springboot.hello.hellospringboot.service;

import springboot.hello.hellospringboot.entity.Company;
import com.baomidou.mybatisplus.service.IService;

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

}
