package springboot.hello.hellospringboot.dao.destDao;

import springboot.hello.hellospringboot.entity.Stock;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
public interface StockDao extends BaseMapper<Stock> {

    public void cleanStock();

}