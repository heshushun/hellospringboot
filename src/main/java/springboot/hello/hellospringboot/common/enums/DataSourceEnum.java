package springboot.hello.hellospringboot.common.enums;

/**
 * <p>
 * 数据源 枚举类 用于存放数据源名称
 * </p>
 *
 * @author hss
 * @since 2018-11-08
 */
public enum DataSourceEnum {

    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
