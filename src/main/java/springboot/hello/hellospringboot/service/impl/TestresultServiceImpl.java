package springboot.hello.hellospringboot.service.impl;

import springboot.hello.hellospringboot.entity.Testresult;
import springboot.hello.hellospringboot.dao.destDao.TestresultDao;
import springboot.hello.hellospringboot.request.Req700015;
import springboot.hello.hellospringboot.service.TestresultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 测试结果表 服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
@Service
public class TestresultServiceImpl extends ServiceImpl<TestresultDao, Testresult> implements TestresultService {

    /**
     * 获取列表 用于导出
     * @param req
     * @return
     */
    @Override
    public List<Testresult> list(Req700015 req) {
        return this.baseMapper.list(req);
    }
}
