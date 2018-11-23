package springboot.hello.hellospringboot.dao.destDao;

import springboot.hello.hellospringboot.entity.Testresult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import springboot.hello.hellospringboot.request.Req700015;

import java.util.List;

/**
 * <p>
  * 测试结果表 Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
public interface TestresultDao extends BaseMapper<Testresult> {

    public List<Testresult> list(Req700015 req);

}