package springboot.hello.hellospringboot.service;

import springboot.hello.hellospringboot.entity.Testresult;
import com.baomidou.mybatisplus.service.IService;
import springboot.hello.hellospringboot.request.Req700015;

import java.util.List;

/**
 * <p>
 * 测试结果表 服务类
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
public interface TestresultService extends IService<Testresult> {

    public List<Testresult> list(Req700015 req);

}
