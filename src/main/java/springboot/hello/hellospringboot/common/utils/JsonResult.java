package springboot.hello.hellospringboot.common.utils;

/**
 * @Author: hss
 * @Date: 2018/10/30
 * @Desc: json格式 响应结果
 */
public class JsonResult {
    /**
     * 标示请求结果为成功
     */
    private Boolean success = Boolean.TRUE;

    /**
     * controller 方法返回数据对象
     */
    private Object data;

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
