package springboot.hello.hellospringboot.controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.response.ErrorData;
import springboot.hello.hellospringboot.response.QuotationData;
import springboot.hello.hellospringboot.request.Req700021;
import springboot.hello.hellospringboot.response.BaseResp;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 测试结果表 前端控制器
 * </p>
 *
 * @author hss
 * @since 2018-11-23
 */
@RestController
@RequestMapping("/compara/quotation")
public class ComparaController {

    private  final static Logger logger = LoggerFactory.getLogger(ComparaController.class);

    /**
     * 日K 行情 比对
     * @return
     */
    @RequestMapping(value = "/dayK",method = RequestMethod.POST)
    public BaseResp comparaDayK(@Valid Req700021 req) throws JSONException {

        Boolean result = true; // 用来返回 结果标志
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情
        String prodCode = req.getProdCode();        // 产品代码

        /*if(realResult.indexOf(prodCode)==-1){
            return  new BaseResp(Boolean.FALSE,"产品代码 传错了");
        }*/

        JSONObject realRs = new JSONObject(realResult);
        JSONArray expectRs = new JSONArray(expectResult);// 金交所行情

        JSONObject realData = realRs.getJSONObject("data");
        JSONArray fields = realData.getJSONArray("fields");
        String code = realData.getJSONObject("candle").keys().next().toString();// 自己去 云赢行情里取 产品代码
        logger.info("产品代码 === "+code);
        JSONArray quotationList = realData.getJSONObject("candle").getJSONArray(code); // 行情数组 List
        logger.info("行情列表 === "+quotationList.toString());

        ArrayList<Double> realArraySingle = new ArrayList<Double>(); // 用来存放 每天的云赢行情

        int index = 0;// 表示 金交所行情的下标
        for(int i= 0; i<quotationList.length();i++){

            // 将 JSONArray 转化为 ArrayList
            JSONArray quotationSingle = quotationList.getJSONArray(i);
            for(int j= 0; j<quotationSingle.length();j++){
                if(!quotationSingle.getString(j).equals("null")) {
                    realArraySingle.add(quotationSingle.getDouble(j));
                }else{
                    realArraySingle.add(0.0);
                }
            }

            // 先判断日期是否一致，若不一致 不进来
            if (quotationSingle.getString(0).equals(expectRs.getJSONObject(index).get("f_date").toString())) {

                QuotationData quotationData = new QuotationData();
                quotationData.setDate(expectRs.getJSONObject(index).get("f_date").toString());
                List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List

                if (Double.valueOf((Double) expectRs.getJSONObject(index).get("f_open_price")) * 1000 != realArraySingle.get(5)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_open_price");
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_open_price")) * 1000));
                    errorData.setrValue(String.valueOf(realArraySingle.get(5)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (Double.valueOf((Double) expectRs.getJSONObject(index).get("f_close_price")) * 1000 != realArraySingle.get(6)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_close_price");
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_close_price"))*1000));
                    errorData.setrValue(String.valueOf(realArraySingle.get(6)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (Double.valueOf((Double) expectRs.getJSONObject(index).get("f_max_price")) * 1000 != realArraySingle.get(2)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_max_price");
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_max_price")) * 1000));
                    errorData.setrValue(String.valueOf(realArraySingle.get(2)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (Double.valueOf((Double) expectRs.getJSONObject(index).get("f_min_price")) * 1000 != realArraySingle.get(7)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_min_price");
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_min_price")) * 1000));
                    errorData.setrValue(String.valueOf(realArraySingle.get(7)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (Double.valueOf(expectRs.getJSONObject(index).get("f_total").toString()) * 1000 != realArraySingle.get(3)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_total");
                    errorData.seteValue(String.valueOf(Double.valueOf(expectRs.getJSONObject(index).get("f_total").toString()) * 1000));
                    errorData.setrValue(String.valueOf(realArraySingle.get(3)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (Double.valueOf((Double) expectRs.getJSONObject(index).get("f_money")) != realArraySingle.get(8)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField("f_money");
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_money"))));
                    errorData.setrValue(String.valueOf(realArraySingle.get(8)));
                    errorDataList.add(errorData);
                    result = false;
                }

                quotationData.setErrorList(errorDataList);
                errorResultList.add(quotationData);
                index ++;
            }

            realArraySingle.clear();
        }

        logger.info("错误消息列表 === "+errorResultList.toString());

        return  new BaseResp(result,errorResultList);
    }




    public static void main(String[] args) throws JSONException {

        String expectRs = "[{\"f_open_price\":3646.0,\"f_close_price\":3655.0,\"f_max_price\":3658.0,\"f_min_price\":3634.0,\"f_total\":2775262,\"f_money\":1.0120028E7,\"f_date\":20190102},{\"f_open_price\":3658.0,\"f_close_price\":3705.0,\"f_max_price\":3711.0,\"f_min_price\":3646.0,\"f_total\":6771660,\"f_money\":2.4915689E7,\"f_date\":20190103},{\"f_open_price\":3699.0,\"f_close_price\":3722.0,\"f_max_price\":3754.0,\"f_min_price\":3690.0,\"f_total\":7320474,\"f_money\":2.723243E7,\"f_date\":20190104},{\"f_open_price\":3720.0,\"f_close_price\":3722.0,\"f_max_price\":3734.0,\"f_min_price\":3688.0,\"f_total\":7611410,\"f_money\":2.8268655E7,\"f_date\":20190107},{\"f_open_price\":3725.0,\"f_close_price\":3688.0,\"f_max_price\":3738.0,\"f_min_price\":3676.0,\"f_total\":5475294,\"f_money\":2.0281448E7,\"f_date\":20190108},{\"f_open_price\":3696.0,\"f_close_price\":3697.0,\"f_max_price\":3708.0,\"f_min_price\":3683.0,\"f_total\":3710286,\"f_money\":1.3714851E7,\"f_date\":20190109},{\"f_open_price\":3697.0,\"f_close_price\":3712.0,\"f_max_price\":3724.0,\"f_min_price\":3681.0,\"f_total\":5253276,\"f_money\":1.9477718E7,\"f_date\":20190110}]";
        String realRs = "{\"errorCode\":0,\"errorInfo\":\"SUCCESS\",\"data\":{\"fields\":[\"date\",\"amount\",\"high_px\",\"business_amount\",\"preclose_px\",\"open_px\",\"close_px\",\"low_px\",\"business_balance\",\"turnover_ratio\"],\"candle\":{\"Ag(T+D).SGEX\":[[20190102,null,3658000,2775262000,3632000,3646000,3655000,3634000,1012002781400000,0],[20190103,null,3711000,6771660,3654000,3658000,3705000,3646000,2491568926600000,0],[20190104,10234068,3754000,7320474,3705000,3699000,3722000,3690000,27232430380,0],[20190107,10215960,3734000,7611410,3722000,3720000,3722000,3688000,28268654926,0],[20190108,10221146,3738000,5475294,3722000,3725000,3688000,3676000,20281447698,0],[20190109,10262936,3708000,3710286,3688000,3696000,3697000,3683000,13714850864,0],[20190110,10490486,3724000,5253276,3697000,3697000,3712000,3681000,19477717520,0]]}}}";
        String code = "Ag(T+D).SGEX";

        ComparaController compara = new ComparaController();
        Req700021 req700021 = new Req700021();
        req700021.setExpectResult(expectRs);
        req700021.setRealResult(realRs);
        req700021.setProdCode(code);
        compara.comparaDayK(req700021);
    }

}
