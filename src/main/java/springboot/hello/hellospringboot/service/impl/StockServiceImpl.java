package springboot.hello.hellospringboot.service.impl;

import springboot.hello.hellospringboot.entity.Stock;
import springboot.hello.hellospringboot.dao.destDao.StockDao;
import springboot.hello.hellospringboot.service.StockService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockDao, Stock> implements StockService {

    @Override
    public void addStock(Stock stock) {
        this.baseMapper.insert(stock);
    }

    @Override
    public void cleanStock() {
        this.baseMapper.cleanStock();
    }

}
