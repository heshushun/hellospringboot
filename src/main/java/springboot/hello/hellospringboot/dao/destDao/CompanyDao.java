package springboot.hello.hellospringboot.dao.destDao;

import springboot.hello.hellospringboot.entity.Company;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
public interface CompanyDao extends BaseMapper<Company> {

    // 根据股票代码删除 公司
    public int deleteByStockCode (String stockCode);

}