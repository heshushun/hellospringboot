package springboot.hello.hellospringboot.response;

/**
 * @Author: hss
 * @Date: 2018/10/09
 * @Desc: 基础返回信息
 */
public class BaseResp<T> {
    private Boolean success=Boolean.TRUE;
    private T data;

    public BaseResp() {
    }

    public BaseResp(T data) {
        this.success = Boolean.TRUE;
        this.data = data;
    }

    public BaseResp(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
