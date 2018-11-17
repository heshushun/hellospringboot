package springboot.hello.hellospringboot.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.hello.hellospringboot.common.annotation.DataSource;
import springboot.hello.hellospringboot.common.cache.CacheConstant;
import springboot.hello.hellospringboot.common.cache.StockCache;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.common.utils.BeanUtils;
import springboot.hello.hellospringboot.dao.yunDao.YunStockinfoDao;
import springboot.hello.hellospringboot.entity.Stockinfo;
import springboot.hello.hellospringboot.dao.destDao.StockinfoDao;
import springboot.hello.hellospringboot.service.StockinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final  static Logger log = LoggerFactory.getLogger(StockinfoServiceImpl.class);

    @Autowired
    YunStockinfoDao yunStockinfoDao;

    @Autowired
    StockinfoDao stockinfoDao;

    @Autowired
    private StockCache stockCache;

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
     * 从数据库 中 获取 股票列表
     * @return
     */
    @Override
    public List<Stockinfo> list() {
        return this.baseMapper.list();
    }



    /**
     * 初始化股票列表到缓存
     * @param stockList
     */
    @Override
    public void initToCache(List<Stockinfo> stockList){
        try {
            if(stockList == null || stockList.isEmpty()){
                stockList = yunStockinfoDao.list();
            }
            stockCache.init(stockList, CacheConstant.STOCK_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("初始化股票到缓存失败！");
        }
    }

    /**
     * 从缓存中取股票数据，并进行属性转换
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> stocklist(Class<T> clazz){
        //从缓存中取数据
        List<Stockinfo> stockList = this.getListFromCache();

        // 如果缓存中没有数据则 去数据库中取
        if(stockList == null || stockList.isEmpty()){
            stockList = this.list();
        }
        // 类属性转换
        List<T> list = new ArrayList<T>();
        for(Stockinfo stockinfo : stockList){
            if(Stockinfo.class.equals(clazz)){
                list.add((T)stockinfo);
            } else {
                list.add(BeanUtils.copyProperties(stockinfo, clazz));
            }
        }
        return list;
    }

    /**
     * 从缓存获取股票列表
     * @return
     */
    public List getListFromCache(){
        try {
            return stockCache.getList(CacheConstant.STOCK_LIST);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("从缓存获取股票列表失败！");
        }
        return new ArrayList<Stockinfo>();
    }


}
