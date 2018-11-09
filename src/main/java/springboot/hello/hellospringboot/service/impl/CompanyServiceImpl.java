package springboot.hello.hellospringboot.service.impl;

import springboot.hello.hellospringboot.entity.Company;
import springboot.hello.hellospringboot.dao.destDao.CompanyDao;
import springboot.hello.hellospringboot.service.CompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company> implements CompanyService {
	
}
