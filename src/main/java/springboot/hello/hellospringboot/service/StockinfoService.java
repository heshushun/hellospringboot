package springboot.hello.hellospringboot.service;

import org.apache.ibatis.annotations.Mapper;
import springboot.hello.hellospringboot.entity.Stockinfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hss
 * @since 2018-11-09
 */
public interface StockinfoService extends IService<Stockinfo> {


    // 源库获取股票列表
    public List<Stockinfo> selectYunStockinfoList();

    // 清空目标库 股票
    public int deleteStocksInfo();

    //批量更新证券代码信息
    public int insertBatchStocksInfo(List<Stockinfo> stockinfoLists);

    //获取所有股票列表
    public List<Stockinfo> list ();

    //初始化股票列表到缓存
    public void initToCache(List<Stockinfo> stockList);

    //从缓存中取股票数据
    public <T> List<T> stocklist(Class<T> clazz);

}
