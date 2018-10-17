package springboot.hello.hellospringboot.common.exception;

/**
 * Created by xuqw on 2018/2/26.
 */
public enum ErrorCode {
    // 令牌校验失败
    TOKEN("999", "token validate failure!"),

    // 认证校验失败
    AUTHORIZATION("998", "authorization validate failure!"),

    // 令牌类型校验失败
    TOKEN_TYPE("997", "token type validate failure!"),

    //参数异常
    PARAMETER_EXCEPTION("996","参数不能为空"),

    // 方法参数无效异常
    METHOD_ARG_INVALID_EXP("995", "方法参数无效异常!"),

    // 字段不合法
    PARAMETER_ERROR("994", "字段不合法!"),

    // 系统异常
    SYS_EXCEPTION("500", "system exception!"),

    // 没有权限
    NO_AUTHORITY("401", "无权限！");

    ErrorCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    private String code;
    private String info;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
