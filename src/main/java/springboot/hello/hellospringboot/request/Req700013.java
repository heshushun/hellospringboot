package springboot.hello.hellospringboot.request;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.plugins.Page;
import springboot.hello.hellospringboot.entity.Company;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司 分页的 请求
 */
public class Req700013 extends Page<Company> implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 名称
     */
    private String name;
    /**
     * 简称
     */
    private String abbrName;
    /**
     * 所属省
     */
    private String province;
    /**
     * 所属城市
     */
    private String city;
    /**
     * 上市日期
     */
    private String listedDate;
    /**
     * 每股发行价
     */
    private BigDecimal issuePrice;
    /**
     * 注册资金
     */
    private BigDecimal regCapital;
    /**
     * 法人代表
     */
    private String legalRepr;
    /**
     * 联系电话
     */
    private String contactTel;
    /**
     * 公司网址
     */
    private String website;
    /**
     * 经营范围
     */
    private String businessMajor;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 成立日期
     */
    private String establishmentDate;
    /**
     * 首日开盘价
     */
    private BigDecimal firstOpenPrice;
    /**
     * 曾经用过的名称
     */
    private String usedNames;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getListedDate() {
        return listedDate;
    }

    public void setListedDate(String listedDate) {
        this.listedDate = listedDate;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public String getLegalRepr() {
        return legalRepr;
    }

    public void setLegalRepr(String legalRepr) {
        this.legalRepr = legalRepr;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusinessMajor() {
        return businessMajor;
    }

    public void setBusinessMajor(String businessMajor) {
        this.businessMajor = businessMajor;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public BigDecimal getFirstOpenPrice() {
        return firstOpenPrice;
    }

    public void setFirstOpenPrice(BigDecimal firstOpenPrice) {
        this.firstOpenPrice = firstOpenPrice;
    }

    public String getUsedNames() {
        return usedNames;
    }

    public void setUsedNames(String usedNames) {
        this.usedNames = usedNames;
    }
}
