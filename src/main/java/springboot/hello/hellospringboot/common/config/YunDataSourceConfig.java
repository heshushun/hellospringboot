package springboot.hello.hellospringboot.common.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * <p>
 * 源 数据源 配置（多数据源配置，如果需要用，将下面两个注解注释去掉）
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
//@Configuration
//@MapperScan(basePackages = {"springboot.hello.hellospringboot.dao.yunDao"}, sqlSessionFactoryRef = "yunSqlSessionFactory")
public class YunDataSourceConfig {

    // 数据源配置
    @Bean(name = "yunDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.druid.yun")
    public DataSource yunDataSource() {
        return DataSourceBuilder.create().build();
    }

    // SQL会话工厂
    @Bean(name = "yunSqlSessionFactory")
    public SqlSessionFactory yunSqlSessionFactory(@Qualifier("yunDataSource") DataSource yunDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(yunDataSource);

        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/*/*Dao.xml");
        sessionFactory.setMapperLocations(resources);

        return sessionFactory.getObject();
    }

    // SQL会话模板
    @Bean(name = "yunSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("yunSqlSessionFactory") SqlSessionFactory yunSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(yunSqlSessionFactory);
    }

}
