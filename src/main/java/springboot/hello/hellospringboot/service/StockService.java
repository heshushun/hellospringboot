package springboot.hello.hellospringboot.service;

import springboot.hello.hellospringboot.entity.Stock;
import com.baomidou.mybatisplus.service.IService;
import springboot.hello.hellospringboot.entity.UserEntity;

import java.util.Stack;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
public interface StockService extends IService<Stock> {

    public void addStock(Stock stock);
	
}
