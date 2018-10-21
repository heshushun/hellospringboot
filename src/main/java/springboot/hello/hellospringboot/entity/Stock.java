package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@TableName("t_stock")
public class Stock extends Model<Stock> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="stockId", type= IdType.AUTO)
	private Integer stockId;
    /**
     * 股票代码
     */
	private String stockCode;
    /**
     * 股票名称
     */
	private String stockName;
    /**
     * 开盘价
     */
	private BigDecimal openPrice;
    /**
     * 昨收价
     */
	private BigDecimal preClosePrice;
    /**
     * 当前股价
     */
	private BigDecimal curPrice;
    /**
     * 今日最高价
     */
	private BigDecimal highPrice;
    /**
     * 今日最低价
     */
	private BigDecimal lowPrice;
    /**
     * 竞买价
     */
	private BigDecimal competeBuyPrice;
    /**
     * 竞卖价
     */
	private BigDecimal competeSellPrice;
    /**
     * 成交量
     */
	private Integer volume;
    /**
     * 成交额
     */
	private BigDecimal turnover;
    /**
     * 买一量
     */
	private Integer buyNum1;
    /**
     * 买一价
     */
	private BigDecimal buyPrice1;
    /**
     * 买二量
     */
	private Integer buyNum2;
    /**
     * 买二价
     */
	private BigDecimal buyPrice2;
    /**
     * 买三量
     */
	private Integer buyNum3;
    /**
     * 买三价
     */
	private BigDecimal buyPrice3;
    /**
     * 买四量
     */
	private Integer buyNum4;
    /**
     * 买四价
     */
	private BigDecimal buyPrice4;
    /**
     * 买5量
     */
	private Integer buyNum5;
    /**
     * 买5价
     */
	private BigDecimal buyPrice5;
    /**
     * 卖一量
     */
	private Integer sellNum1;
    /**
     * 卖一价
     */
	private BigDecimal sellPrice1;
    /**
     * 卖二量
     */
	private Integer sellNum2;
    /**
     * 卖二价
     */
	private BigDecimal sellPrice2;
    /**
     * 卖三量
     */
	private Integer sellNum3;
    /**
     * 卖三价
     */
	private BigDecimal sellPrice3;
    /**
     * 卖四量
     */
	private Integer sellNum4;
    /**
     * 卖四价
     */
	private BigDecimal sellPrice4;
    /**
     * 卖5量
     */
	private Integer sellNum5;
    /**
     * 卖5价
     */
	private BigDecimal sellPrice5;
    /**
     * 交易日期
     */
	private String dataDate;
    /**
     * 交易时间
     */
	private String dataTime;


	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getPreClosePrice() {
		return preClosePrice;
	}

	public void setPreClosePrice(BigDecimal preClosePrice) {
		this.preClosePrice = preClosePrice;
	}

	public BigDecimal getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getCompeteBuyPrice() {
		return competeBuyPrice;
	}

	public void setCompeteBuyPrice(BigDecimal competeBuyPrice) {
		this.competeBuyPrice = competeBuyPrice;
	}

	public BigDecimal getCompeteSellPrice() {
		return competeSellPrice;
	}

	public void setCompeteSellPrice(BigDecimal competeSellPrice) {
		this.competeSellPrice = competeSellPrice;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public Integer getBuyNum1() {
		return buyNum1;
	}

	public void setBuyNum1(Integer buyNum1) {
		this.buyNum1 = buyNum1;
	}

	public BigDecimal getBuyPrice1() {
		return buyPrice1;
	}

	public void setBuyPrice1(BigDecimal buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}

	public Integer getBuyNum2() {
		return buyNum2;
	}

	public void setBuyNum2(Integer buyNum2) {
		this.buyNum2 = buyNum2;
	}

	public BigDecimal getBuyPrice2() {
		return buyPrice2;
	}

	public void setBuyPrice2(BigDecimal buyPrice2) {
		this.buyPrice2 = buyPrice2;
	}

	public Integer getBuyNum3() {
		return buyNum3;
	}

	public void setBuyNum3(Integer buyNum3) {
		this.buyNum3 = buyNum3;
	}

	public BigDecimal getBuyPrice3() {
		return buyPrice3;
	}

	public void setBuyPrice3(BigDecimal buyPrice3) {
		this.buyPrice3 = buyPrice3;
	}

	public Integer getBuyNum4() {
		return buyNum4;
	}

	public void setBuyNum4(Integer buyNum4) {
		this.buyNum4 = buyNum4;
	}

	public BigDecimal getBuyPrice4() {
		return buyPrice4;
	}

	public void setBuyPrice4(BigDecimal buyPrice4) {
		this.buyPrice4 = buyPrice4;
	}

	public Integer getBuyNum5() {
		return buyNum5;
	}

	public void setBuyNum5(Integer buyNum5) {
		this.buyNum5 = buyNum5;
	}

	public BigDecimal getBuyPrice5() {
		return buyPrice5;
	}

	public void setBuyPrice5(BigDecimal buyPrice5) {
		this.buyPrice5 = buyPrice5;
	}

	public Integer getSellNum1() {
		return sellNum1;
	}

	public void setSellNum1(Integer sellNum1) {
		this.sellNum1 = sellNum1;
	}

	public BigDecimal getSellPrice1() {
		return sellPrice1;
	}

	public void setSellPrice1(BigDecimal sellPrice1) {
		this.sellPrice1 = sellPrice1;
	}

	public Integer getSellNum2() {
		return sellNum2;
	}

	public void setSellNum2(Integer sellNum2) {
		this.sellNum2 = sellNum2;
	}

	public BigDecimal getSellPrice2() {
		return sellPrice2;
	}

	public void setSellPrice2(BigDecimal sellPrice2) {
		this.sellPrice2 = sellPrice2;
	}

	public Integer getSellNum3() {
		return sellNum3;
	}

	public void setSellNum3(Integer sellNum3) {
		this.sellNum3 = sellNum3;
	}

	public BigDecimal getSellPrice3() {
		return sellPrice3;
	}

	public void setSellPrice3(BigDecimal sellPrice3) {
		this.sellPrice3 = sellPrice3;
	}

	public Integer getSellNum4() {
		return sellNum4;
	}

	public void setSellNum4(Integer sellNum4) {
		this.sellNum4 = sellNum4;
	}

	public BigDecimal getSellPrice4() {
		return sellPrice4;
	}

	public void setSellPrice4(BigDecimal sellPrice4) {
		this.sellPrice4 = sellPrice4;
	}

	public Integer getSellNum5() {
		return sellNum5;
	}

	public void setSellNum5(Integer sellNum5) {
		this.sellNum5 = sellNum5;
	}

	public BigDecimal getSellPrice5() {
		return sellPrice5;
	}

	public void setSellPrice5(BigDecimal sellPrice5) {
		this.sellPrice5 = sellPrice5;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.stockId;
	}

	@Override
	public String toString() {
		return "Stock{" +
			"stockId=" + stockId +
			", stockCode=" + stockCode +
			", stockName=" + stockName +
			", openPrice=" + openPrice +
			", preClosePrice=" + preClosePrice +
			", curPrice=" + curPrice +
			", highPrice=" + highPrice +
			", lowPrice=" + lowPrice +
			", competeBuyPrice=" + competeBuyPrice +
			", competeSellPrice=" + competeSellPrice +
			", volume=" + volume +
			", turnover=" + turnover +
			", buyNum1=" + buyNum1 +
			", buyPrice1=" + buyPrice1 +
			", buyNum2=" + buyNum2 +
			", buyPrice2=" + buyPrice2 +
			", buyNum3=" + buyNum3 +
			", buyPrice3=" + buyPrice3 +
			", buyNum4=" + buyNum4 +
			", buyPrice4=" + buyPrice4 +
			", buyNum5=" + buyNum5 +
			", buyPrice5=" + buyPrice5 +
			", sellNum1=" + sellNum1 +
			", sellPrice1=" + sellPrice1 +
			", sellNum2=" + sellNum2 +
			", sellPrice2=" + sellPrice2 +
			", sellNum3=" + sellNum3 +
			", sellPrice3=" + sellPrice3 +
			", sellNum4=" + sellNum4 +
			", sellPrice4=" + sellPrice4 +
			", sellNum5=" + sellNum5 +
			", sellPrice5=" + sellPrice5 +
			", dataDate=" + dataDate +
			", dataTime=" + dataTime +
			"}";
	}
}
