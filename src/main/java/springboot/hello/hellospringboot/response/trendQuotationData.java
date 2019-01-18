package springboot.hello.hellospringboot.response;

import java.util.List;

public class trendQuotationData {

    private static final long serialVersionUID = 1L;

    /**
     * 精度
     */
    private Double deviation;
    /**
     * 比较的字段
     */
    private String cmpField;

    /**
     * 错误数量
     */
    private Integer errTotal;

    /**
     * 总数数量
     */
    private Integer cmpTotal;

    /**
     * 错误比例
     */
    private String proportion;

    /**
     * 错误信息
     */
    private List<QuotationData> quotationList;

    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }


    public List<QuotationData> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<QuotationData> quotationList) {
        this.quotationList = quotationList;
    }

    public String getCmpField() {
        return cmpField;
    }

    public void setCmpField(String cmpField) {
        this.cmpField = cmpField;
    }

    public Integer getErrTotal() {
        return errTotal;
    }

    public void setErrTotal(Integer errTotal) {
        this.errTotal = errTotal;
    }

    public Integer getCmpTotal() {
        return cmpTotal;
    }

    public void setCmpTotal(Integer cmpTotal) {
        this.cmpTotal = cmpTotal;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
