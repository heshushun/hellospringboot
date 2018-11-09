package springboot.hello.hellospringboot.common.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 * 通过DataSourceContextHolder 获取数据源变量
 * 用于当作lookupKey取出指定的数据源。
 * </p>
 *
 * @author hss
 * @since 2018-11-08
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}
