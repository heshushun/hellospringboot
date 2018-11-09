package springboot.hello.hellospringboot.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>
 * 地 数据源 配置（多数据源配置，如果需要用，将下面两个注解注释去掉）
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
//@Configuration
//@MapperScan(basePackages = {"springboot.hello.hellospringboot.dao.destDao"}, sqlSessionFactoryRef = "destSqlSessionFactory")
public class DestDataSourceConfig {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
        return paginationInterceptor;
    }

    // 数据源配置
    @Bean(name = "destDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.druid.dest")
    @Primary
    public DataSource destDataSource() {
        return DataSourceBuilder.create().build();
    }

    // TransactionManager
    @Bean(name = "destTransactionManager")
    @Qualifier("destTransactionManager")
    @Primary
    public DataSourceTransactionManager destTransactionManager(@Qualifier("destDataSource") DataSource destDataSource) {
        return new DataSourceTransactionManager(destDataSource);
    }

    // SQL会话工厂
    @Bean(name = "destSqlSessionFactory")
    @Primary
    public SqlSessionFactory destSqlSessionFactory(@Qualifier("destDataSource") DataSource destDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(destDataSource);

        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/*/*Dao.xml");
        sessionFactory.setMapperLocations(resources);

        return sessionFactory.getObject();
    }

    // SQL会话模板
    @Bean(name = "destSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate destSqlSessionTemplate(@Qualifier("destSqlSessionFactory") SqlSessionFactory destSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(destSqlSessionFactory);
    }

}
