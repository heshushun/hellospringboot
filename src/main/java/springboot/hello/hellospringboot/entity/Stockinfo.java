package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

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
 * @since 2018-11-09
 */
@TableName("t_stockinfo")
public class Stockinfo extends Model<Stockinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 股票代码
     */
    @TableId("stock_code")
	private String stockCode;
    /**
     * 公司代码
     */
	@TableField("company_code")
	private Integer companyCode;
    /**
     * 内部代码
     */
	@TableField("inner_code")
	private Integer innerCode;


	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Integer getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(Integer innerCode) {
		this.innerCode = innerCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.stockCode;
	}

	@Override
	public String toString() {
		return "Stockinfo{" +
			"stockCode=" + stockCode +
			", companyCode=" + companyCode +
			", innerCode=" + innerCode +
			"}";
	}
}
