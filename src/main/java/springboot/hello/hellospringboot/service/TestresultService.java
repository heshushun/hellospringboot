package springboot.hello.hellospringboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Company;
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

    //测试结果列表（分页）
    public Page<Testresult> selectTestresultPage(Page<Testresult> page, Testresult testresult);

    //获取项目列表
    public List<String> getProjectList (Testresult testresult);

    //获取TS列表
    public List<String> getTsList (Testresult testresult);

    //根据ID获取单条测试结果
    public Testresult getTestresultById(Integer testresultId);

    //清空一天前的测试结果
    public void cleanTestresult();
}
