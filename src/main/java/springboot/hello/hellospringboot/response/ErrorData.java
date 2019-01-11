package springboot.hello.hellospringboot.response;

public class ErrorData {

    /**
     * 错误字段
     */
    private String errorField;

    /**
     * 期望值
     */
    private String eValue;
    /**
     * 实际值
     */
    private String rValue;

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String geteValue() {
        return eValue;
    }

    public void seteValue(String eValue) {
        this.eValue = eValue;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "errorField='" + errorField + '\'' +
                ", eValue='" + eValue + '\'' +
                ", rValue='" + rValue + '\'' +
                '}';
    }
}
