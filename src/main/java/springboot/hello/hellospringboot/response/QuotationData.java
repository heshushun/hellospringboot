package springboot.hello.hellospringboot.response;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.List;

public class QuotationData {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private String date;
    /**
     * 错误信息
     */
    private List<ErrorData> errorList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ErrorData> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorData> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "QuotationData{" +
                "date='" + date + '\'' +
                ", errorList=" + errorList +
                '}';
    }
}
