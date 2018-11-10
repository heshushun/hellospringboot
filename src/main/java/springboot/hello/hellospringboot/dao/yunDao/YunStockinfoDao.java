package springboot.hello.hellospringboot.dao.yunDao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import springboot.hello.hellospringboot.entity.Stockinfo;

import java.util.List;

/**
 * <p>
 *  股票信息 云 Dao接口
 * </p>
 *
 * @author hss
 * @since 2018-11-09
 */
@Mapper
public interface YunStockinfoDao extends BaseMapper<Stockinfo> {

    public List<Stockinfo> list();

}
