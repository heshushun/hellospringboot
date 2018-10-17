package springboot.hello.hellospringboot.common.exception;



/**
 * 业务性异常
 * Created by xuqw on 18/2/1.
 */
public class BizException extends RuntimeException {

    private String code;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
