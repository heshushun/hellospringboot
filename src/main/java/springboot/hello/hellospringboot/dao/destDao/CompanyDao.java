package springboot.hello.hellospringboot.dao.destDao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import springboot.hello.hellospringboot.entity.Company;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

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

    //公司列表（分页）
    public List<Company> selectCompanyList (Pagination page ,Company company);

    //获取行业列表
    public List<String> getIndustryList ();

}