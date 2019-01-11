package springboot.hello.hellospringboot.dao.destDao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.entity.Testresult;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import springboot.hello.hellospringboot.request.Req700015;
import springboot.hello.hellospringboot.request.Req700022;

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

    //测试结果列表（分页）
    public List<Testresult> selectTestresultList (Pagination page , Testresult testresult);

    //获取项目列表
    public List<String> getProjectList (Testresult testresult);

    //清空一天前的测试结果
    public void cleanTestresult();

    //获取TS列表
    public List<String> getTsList (Testresult testresult);

    //根据条件删除测试结果
    public Integer delTestresult(Req700022 req700022);
}