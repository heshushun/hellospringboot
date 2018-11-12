package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hss
 * @since 2018-11-07
 */
@TableName("t_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * 股票代码
     */
    @TableId("stock_code")
	private String stockCode;
    /**
     * 名称
     */
	private String name;
    /**
     * 简称
     */
	@TableField("abbr_name")
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
	@TableField("listed_date")
	private String listedDate;
    /**
     * 每股发行价
     */
	@TableField("issue_price")
	private BigDecimal issuePrice;
    /**
     * 注册资金
     */
	@TableField("reg_capital")
	private BigDecimal regCapital;
    /**
     * 法人代表
     */
	@TableField("legal_repr")
	private String legalRepr;
    /**
     * 联系电话
     */
	@TableField("contact_tel")
	private String contactTel;
    /**
     * 公司网址
     */
	private String website;
    /**
     * 经营范围
     */
	@TableField("business_major")
	private String businessMajor;
    /**
     * 所属行业
     */
	private String industry;
    /**
     * 成立日期
     */
	@TableField("establishment_date")
	private String establishmentDate;
    /**
     * 首日开盘价
     */
	@TableField("first_open_price")
	private BigDecimal firstOpenPrice;
    /**
     * 曾经用过的名称
     */
	@TableField("used_names")
	private String usedNames;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	@Override
	protected Serializable pkVal() {
		return this.stockCode;
	}

	@Override
	public String toString() {
		return "Company{" +
			"stockCode=" + stockCode +
			", name=" + name +
			", abbrName=" + abbrName +
			", province=" + province +
			", city=" + city +
			", listedDate=" + listedDate +
			", issuePrice=" + issuePrice +
			", regCapital=" + regCapital +
			", legalRepr=" + legalRepr +
			", contactTel=" + contactTel +
			", website=" + website +
			", businessMajor=" + businessMajor +
			", industry=" + industry +
			", establishmentDate=" + establishmentDate +
			", firstOpenPrice=" + firstOpenPrice +
			", usedNames=" + usedNames +
			"}";
	}
}
