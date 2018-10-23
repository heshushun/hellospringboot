package springboot.hello.hellospringboot.request;

import java.io.Serializable;

/**
 * 获取签名 入参
 */
public class Req800001 implements Serializable {

    private static final long serialVersionUID = -7323868806548711092L;

    //218006
    private String user_id;
    private String login_account;
    private String login_ticket;
    private String client_uuid;
    private String version;
    private String client;
    private String product_id;
    private String client_token;
    private String user_code;
    private String goods_id;
    private String entrust_price;
    private String used_price_flag;
    private String timestamp;
    private String zl_user_id;
    private String user_name_text;
    private String cert_type;
    private String cert_id;
    private String purchase_type;
    private String bank_code;
    private String card_no;
    private String pay_password;
    private String fund_user_id;

    //218008
    private String order_no;
    private String pay_way;

    //218009
    private String service_type;
    private String goods_type;
    private String nonceStr;

    //218002
    private String grant_type;

    //218003
    private String present_unit;

    //218004
    private String order_type;

    //218005
    private String pay_status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
    }

    public String getLogin_ticket() {
        return login_ticket;
    }

    public void setLogin_ticket(String login_ticket) {
        this.login_ticket = login_ticket;
    }

    public String getClient_uuid() {
        return client_uuid;
    }

    public void setClient_uuid(String client_uuid) {
        this.client_uuid = client_uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getClient_token() {
        return client_token;
    }

    public void setClient_token(String client_token) {
        this.client_token = client_token;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(String entrust_price) {
        this.entrust_price = entrust_price;
    }

    public String getUsed_price_flag() {
        return used_price_flag;
    }

    public void setUsed_price_flag(String used_price_flag) {
        this.used_price_flag = used_price_flag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getZl_user_id() {
        return zl_user_id;
    }

    public void setZl_user_id(String zl_user_id) {
        this.zl_user_id = zl_user_id;
    }

    public String getUser_name_text() {
        return user_name_text;
    }

    public void setUser_name_text(String user_name_text) {
        this.user_name_text = user_name_text;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

    public String getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(String purchase_type) {
        this.purchase_type = purchase_type;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getPay_password() {
        return pay_password;
    }

    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }

    public String getFund_user_id() {
        return fund_user_id;
    }

    public void setFund_user_id(String fund_user_id) {
        this.fund_user_id = fund_user_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getPresent_unit() {
        return present_unit;
    }

    public void setPresent_unit(String present_unit) {
        this.present_unit = present_unit;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
}
