package springboot.hello.hellospringboot.dao.yunDao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import springboot.hello.hellospringboot.entity.Company;

import java.util.List;

public interface YunCompanyDao extends BaseMapper<Company> {

    public List<Company> list ();

    // 根据 股票代码获取 公司
    public Company getCompanyByStockCode(String stockCode);


}
