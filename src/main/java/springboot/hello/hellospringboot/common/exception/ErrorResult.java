package springboot.hello.hellospringboot.common.exception;

/**
 * Created by xuqw on 2018/2/26.
 */
public class ErrorResult {
    private String error_no = "0";
    private String error_info = "";

    public ErrorResult(){}
    public ErrorResult(String error_no, String error_info){
        this.error_no = error_no;
        this.error_info = error_info;
    }

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }
}
