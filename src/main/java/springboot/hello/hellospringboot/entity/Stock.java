package springboot.hello.hellospringboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 股票行情
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
	@TableId(value="stock_id", type= IdType.AUTO)
	private Integer stockId;
    /**
     * 股票代码
     */
	@TableField("stock_code")
	private String stockCode;
    /**
     * 股票名称
     */
	@TableField("stock_name")
	private String stockName;
    /**
     * 开盘价
     */
	@TableField("open_price")
	private Double openPrice;
    /**
     * 昨收价
     */
	@TableField("pre_close_price")
	private Double preClosePrice;
    /**
     * 当前股价
     */
	@TableField("cur_price")
	private Double curPrice;
    /**
     * 今日最高价
     */
	@TableField("high_price")
	private Double highPrice;
    /**
     * 今日最低价
     */
	@TableField("low_price")
	private Double lowPrice;
    /**
     * 竞买价
     */
	@TableField("compete_buy_price")
	private Double competeBuyPrice;
    /**
     * 竞卖价
     */
	@TableField("compete_sell_price")
	private Double competeSellPrice;
    /**
     * 成交量
     */
	@TableField("volume")
	private Integer volume;
    /**
     * 成交额
     */
	@TableField("turnover")
	private Double turnover;
    /**
     * 买一量
     */
	@TableField("buy_num1")
	private Integer buyNum1;
    /**
     * 买一价
     */
	@TableField("buy_price1")
	private Double buyPrice1;
    /**
     * 买二量
     */
	@TableField("buy_num2")
	private Integer buyNum2;
    /**
     * 买二价
     */
	@TableField("buy_price2")
	private Double buyPrice2;
    /**
     * 买三量
     */
	@TableField("buy_num3")
	private Integer buyNum3;
    /**
     * 买三价
     */
	@TableField("buy_price3")
	private Double buyPrice3;
    /**
     * 买四量
     */
	@TableField("buy_num4")
	private Integer buyNum4;
    /**
     * 买四价
     */
	@TableField("buy_price4")
	private Double buyPrice4;
    /**
     * 买5量
     */
	@TableField("buy_num5")
	private Integer buyNum5;
    /**
     * 买5价
     */
	@TableField("buy_price5")
	private Double buyPrice5;
    /**
     * 卖一量
     */
	@TableField("sell_num1")
	private Integer sellNum1;
    /**
     * 卖一价
     */
	@TableField("sell_price1")
	private Double sellPrice1;
    /**
     * 卖二量
     */
	@TableField("sell_num2")
	private Integer sellNum2;
    /**
     * 卖二价
     */
	@TableField("sell_price2")
	private Double sellPrice2;
    /**
     * 卖三量
     */
	@TableField("sell_num3")
	private Integer sellNum3;
    /**
     * 卖三价
     */
	@TableField("sell_price3")
	private Double sellPrice3;
    /**
     * 卖四量
     */
	@TableField("sell_num4")
	private Integer sellNum4;
    /**
     * 卖四价
     */
	@TableField("sell_price4")
	private Double sellPrice4;
    /**
     * 卖5量
     */
	@TableField("sell_num5")
	private Integer sellNum5;
    /**
     * 卖5价
     */
	@TableField("sell_price5")
	private Double sellPrice5;
    /**
     * 交易日期
     */
	@TableField("data_date")
	private String dataDate;
    /**
     * 交易时间
     */
	@TableField("data_time")
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

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getPreClosePrice() {
		return preClosePrice;
	}

	public void setPreClosePrice(Double preClosePrice) {
		this.preClosePrice = preClosePrice;
	}

	public Double getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(Double curPrice) {
		this.curPrice = curPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getCompeteBuyPrice() {
		return competeBuyPrice;
	}

	public void setCompeteBuyPrice(Double competeBuyPrice) {
		this.competeBuyPrice = competeBuyPrice;
	}

	public Double getCompeteSellPrice() {
		return competeSellPrice;
	}

	public void setCompeteSellPrice(Double competeSellPrice) {
		this.competeSellPrice = competeSellPrice;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Integer getBuyNum1() {
		return buyNum1;
	}

	public void setBuyNum1(Integer buyNum1) {
		this.buyNum1 = buyNum1;
	}

	public Double getBuyPrice1() {
		return buyPrice1;
	}

	public void setBuyPrice1(Double buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}

	public Integer getBuyNum2() {
		return buyNum2;
	}

	public void setBuyNum2(Integer buyNum2) {
		this.buyNum2 = buyNum2;
	}

	public Double getBuyPrice2() {
		return buyPrice2;
	}

	public void setBuyPrice2(Double buyPrice2) {
		this.buyPrice2 = buyPrice2;
	}

	public Integer getBuyNum3() {
		return buyNum3;
	}

	public void setBuyNum3(Integer buyNum3) {
		this.buyNum3 = buyNum3;
	}

	public Double getBuyPrice3() {
		return buyPrice3;
	}

	public void setBuyPrice3(Double buyPrice3) {
		this.buyPrice3 = buyPrice3;
	}

	public Integer getBuyNum4() {
		return buyNum4;
	}

	public void setBuyNum4(Integer buyNum4) {
		this.buyNum4 = buyNum4;
	}

	public Double getBuyPrice4() {
		return buyPrice4;
	}

	public void setBuyPrice4(Double buyPrice4) {
		this.buyPrice4 = buyPrice4;
	}

	public Integer getBuyNum5() {
		return buyNum5;
	}

	public void setBuyNum5(Integer buyNum5) {
		this.buyNum5 = buyNum5;
	}

	public Double getBuyPrice5() {
		return buyPrice5;
	}

	public void setBuyPrice5(Double buyPrice5) {
		this.buyPrice5 = buyPrice5;
	}

	public Integer getSellNum1() {
		return sellNum1;
	}

	public void setSellNum1(Integer sellNum1) {
		this.sellNum1 = sellNum1;
	}

	public Double getSellPrice1() {
		return sellPrice1;
	}

	public void setSellPrice1(Double sellPrice1) {
		this.sellPrice1 = sellPrice1;
	}

	public Integer getSellNum2() {
		return sellNum2;
	}

	public void setSellNum2(Integer sellNum2) {
		this.sellNum2 = sellNum2;
	}

	public Double getSellPrice2() {
		return sellPrice2;
	}

	public void setSellPrice2(Double sellPrice2) {
		this.sellPrice2 = sellPrice2;
	}

	public Integer getSellNum3() {
		return sellNum3;
	}

	public void setSellNum3(Integer sellNum3) {
		this.sellNum3 = sellNum3;
	}

	public Double getSellPrice3() {
		return sellPrice3;
	}

	public void setSellPrice3(Double sellPrice3) {
		this.sellPrice3 = sellPrice3;
	}

	public Integer getSellNum4() {
		return sellNum4;
	}

	public void setSellNum4(Integer sellNum4) {
		this.sellNum4 = sellNum4;
	}

	public Double getSellPrice4() {
		return sellPrice4;
	}

	public void setSellPrice4(Double sellPrice4) {
		this.sellPrice4 = sellPrice4;
	}

	public Integer getSellNum5() {
		return sellNum5;
	}

	public void setSellNum5(Integer sellNum5) {
		this.sellNum5 = sellNum5;
	}

	public Double getSellPrice5() {
		return sellPrice5;
	}

	public void setSellPrice5(Double sellPrice5) {
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
