package springboot.hello.hellospringboot.dao.destDao;

import org.apache.ibatis.annotations.Mapper;
import springboot.hello.hellospringboot.entity.Stockinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2018-11-09
 */
@Mapper
public interface StockinfoDao extends BaseMapper<Stockinfo> {

    // 清空目标库 股票
    public int deleteStocksInfo();

    //批量更新证券代码信息
    public int insertBatchStocksInfo(List<Stockinfo> stockinfoLists);

    //获取所有股票列表
    public List<Stockinfo> list ();

}