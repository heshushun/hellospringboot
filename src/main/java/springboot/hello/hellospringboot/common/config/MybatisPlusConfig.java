package springboot.hello.hellospringboot.common.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import springboot.hello.hellospringboot.common.enums.DataSourceEnum;
import springboot.hello.hellospringboot.common.multiple.MultipleDataSource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
/**
 * <p>
 * MybatisPlus配置 （地 数据源 配置）
 * </p>
 *
 * @author hss
 * @since 2018-10-09
 */
@Configuration
@MapperScan(basePackages = {"springboot.hello.hellospringboot.dao.*"})
public class MybatisPlusConfig {

    private final static Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

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


    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dest" )
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.yun" )
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB1.getValue(), db1);
        targetDataSources.put(DataSourceEnum.DB2.getValue(), db2);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(db1);
        return multipleDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(multipleDataSource(db1(),db2()));//添加数据源

        MybatisConfiguration configuration = new MybatisConfiguration();// 定义配置
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactory.setConfiguration(configuration);//添加配置

        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor() //添加分页功能
        });

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Dao.xml");
        sqlSessionFactory.setMapperLocations(resources); // 添加 mapper

        return sqlSessionFactory.getObject();
    }

}
