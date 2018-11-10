package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.dao.yunDao.YunStockinfoDao;
import springboot.hello.hellospringboot.entity.Stockinfo;
import springboot.hello.hellospringboot.dao.destDao.StockinfoDao;
import springboot.hello.hellospringboot.service.StockinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  股票信息 服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-11-09
 */
@Service
public class StockinfoServiceImpl extends ServiceImpl<StockinfoDao, Stockinfo> implements StockinfoService {

    @Autowired
    YunStockinfoDao yunStockinfoDao;

    @Autowired
    StockinfoDao stockinfoDao;


    /**
     * 从源库采集 股票数据
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<Stockinfo> selectYunStockinfoList() {
        return yunStockinfoDao.list();
    }


    /**
     * 清空目的库 股票数据
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.DB1)
    public int deleteStocksInfo() {
        return stockinfoDao.deleteStocksInfo();
    }


    /**
     * 批量更新证券代码信息
     * @return
     */
    @Override
    @DataSource(DataSourceEnum.DB1)
    public int insertBatchStocksInfo(List<Stockinfo> stockinfoLists) {
        return stockinfoDao.insertBatchStocksInfo(stockinfoLists);
    }

    /**
     * 获取 股票列表
     * @return
     */
    @Override
    public List<Stockinfo> list() {
        return this.baseMapper.list();
    }


}
