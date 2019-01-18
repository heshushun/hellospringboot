package springboot.hello.hellospringboot.controller;


import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.request.Req700027;
import springboot.hello.hellospringboot.request.Req700028;
import springboot.hello.hellospringboot.request.Req700029;
import springboot.hello.hellospringboot.response.ErrorData;
import springboot.hello.hellospringboot.response.QuotationData;
import springboot.hello.hellospringboot.request.Req700021;
import springboot.hello.hellospringboot.response.BaseResp;
import springboot.hello.hellospringboot.response.trendQuotationData;

import javax.validation.Valid;
import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private final static Logger logger = LoggerFactory.getLogger(ComparaController.class);

    /**
     * K线 行情 比对
     *
     * @return
     */
    @RequestMapping(value = "/KLine", method = RequestMethod.POST)
    public BaseResp comparaKLine(@Valid Req700021 req) throws JSONException, ParseException {

        Boolean result = true; // 用来返回 结果标志
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情
        String prodCode = req.getProdCode();        // 产品代码
        String type = req.getType();                // K 线类型  1：代表分钟 2：代表日周月
        Integer dayCount = req.getDayCount();       // 偏移天数
        if (dayCount == null) {
            dayCount = 0;
        }

        JSONObject realRs = new JSONObject(realResult);  // 云赢行情
        JSONArray expectRs = new JSONArray(expectResult);// 金交所行情
        Integer expectRsLengh = expectRs.length();       // 金交所数量
        if (expectRsLengh == 0) {
            return new BaseResp(Boolean.FALSE, "金交所数据为空");
        }

        JSONObject realData = realRs.getJSONObject("data");
        JSONObject candle = realData.getJSONObject("candle");
        List<String> keysList = new ArrayList<String>();
        Iterator<String> it = candle.keys();
        while (it.hasNext()) {
            keysList.add(it.next());// 获得key
        }
        JSONArray fields = candle.getJSONArray("fields");
        String code = "";       // 自己去 云赢行情里取 产品代码
        for (String key : keysList) {
            if (!key.equals("fields")) {
                code = key;
            }
        }
        logger.info("keysList === " + keysList);
        logger.info("产品代码 === " + code);
        JSONArray quotationList = candle.getJSONArray(code);      // 行情数组 List
        logger.info("行情列表 === " + quotationList.toString());

        ArrayList<Double> realArraySingle = new ArrayList<Double>(); // 用来存放 每天的云赢行情
        Boolean isCompara = false; // 标志 全部数据 是否比对过
        for (int i = 0; i < quotationList.length(); i++) {

            // 将 JSONArray 转化为 ArrayList
            JSONArray quotationSingle = quotationList.getJSONArray(i);
            for (int j = 0; j < quotationSingle.length(); j++) {
                if (!quotationSingle.getString(j).equals("null")) {
                    realArraySingle.add(quotationSingle.getDouble(j));
                } else {
                    realArraySingle.add(0.0);
                }
            }

            String rDate = ""; // 云赢时间
            String eDate = "";   // 金交所时间

            QuotationData quotationData = new QuotationData();
            quotationData.setDate(quotationSingle.getString(0));
            List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List
            Boolean isbijiao = false;         // 判断该条数据有没有 比较过
            for (int k = 0; k < expectRsLengh; k++) {

                if (type.equals("2")) { // 日期
                    rDate = quotationSingle.getString(0);
                    eDate = expectRs.getJSONObject(k).get("f_date").toString();
                } else { // 分钟 时间
                    rDate = quotationSingle.getString(0).substring(3);
                    eDate = expectRs.getJSONObject(k).get("f_date").toString().substring(1);

                    String time = expectRs.getJSONObject(k).get("f_date").toString().substring(6);
                    if (Integer.valueOf(time) >= 2000 && Integer.valueOf(time) <= 2359) {
                        eDate = getDateAfter(eDate, dayCount);
                    }

                }

                // 先判断日期是否一致，若不一致 不进来
                if (rDate.equals(eDate)) {
                    isCompara = true; // 进来了说明 比对过了
                    isbijiao = true;
                    for (int key = 0; key < fields.length(); key++) {
                        if (fields.getString(key).equals("open_px")) {
                            if (!Double.valueOf((Double) expectRs.getJSONObject(k).get("f_open_price")).equals(realArraySingle.get(key))) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_open_price"))));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                        if (fields.getString(key).equals("close_px")) {
                            if (!Double.valueOf((Double) expectRs.getJSONObject(k).get("f_close_price")).equals(realArraySingle.get(key))) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_close_price"))));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                        if (fields.getString(key).equals("high_px")) {
                            if (!Double.valueOf((Double) expectRs.getJSONObject(k).get("f_max_price")).equals(realArraySingle.get(key))) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_max_price"))));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                        if (fields.getString(key).equals("low_px")) {
                            if (!Double.valueOf((Double) expectRs.getJSONObject(k).get("f_min_price")).equals(realArraySingle.get(key))) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_min_price"))));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                        if (fields.getString(key).equals("business_amount")) {
                            if (!Double.valueOf(expectRs.getJSONObject(k).get("f_total").toString()).equals(realArraySingle.get(key))) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf(expectRs.getJSONObject(k).get("f_total").toString())));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                        if (fields.getString(key).equals("business_balance")) {
                            if (!realArraySingle.get(key).equals(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_money")) * 1000)) {
                                ErrorData errorData = new ErrorData();
                                errorData.setErrorField(fields.getString(key));
                                errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k).get("f_money")) * 1000));
                                errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                                errorDataList.add(errorData);
                                result = false;
                            }
                        }
                    }
                    break;
                }
            }
            if (!isbijiao) {
                ErrorData errorData = new ErrorData();
                errorData.setErrorField("该日期没有对应金交所行情");
                errorDataList.add(errorData);
            }
            quotationData.setErrorList(errorDataList);
            errorResultList.add(quotationData);

            realArraySingle.clear();
        }

        logger.info("错误消息列表 === " + errorResultList.toString());

        if (!isCompara) {
            return new BaseResp(Boolean.FALSE, "两者没有一致的日期/时间，因此没有比对过！！！");
        }

        return new BaseResp(result, errorResultList);
    }


    /**
     * 分时 行情 比对
     *
     * @return
     */
    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    public BaseResp comparaTrend(@Valid Req700029 req) throws JSONException {
        Boolean result = true; // 用来返回 结果标志

        trendQuotationData trendData = new trendQuotationData();  // 响应结果信息
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情
        Double deviation = req.getDeviation();      // 允许的误差
        if (deviation == null) {
            deviation = 0.00;
        }

        JSONObject realRs = new JSONObject(realResult);
        JSONArray expectRs = new JSONArray(expectResult);// 金交所行情
        Integer expectRsLengh = expectRs.length();       // 金交所K线数量
        if (expectRsLengh == 0) {
            return new BaseResp(Boolean.FALSE, "金交所数据为空");
        }

        JSONObject realData = realRs.getJSONObject("data");
        JSONObject trend = realData.getJSONObject("trend");
        List<String> keysList = new ArrayList<String>();
        Iterator<String> it = trend.keys();
        while (it.hasNext()) {
            keysList.add(it.next());// 获得key
        }
        JSONArray fields = trend.getJSONArray("fields");
        String code = "";       // 自己去 云赢行情里取 产品代码
        for (String key : keysList) {
            if (!key.equals("fields")) {
                code = key;
            }
        }
        logger.info("产品代码 === " + code);
        JSONArray quotationList = trend.getJSONArray(code);      // 云赢行情数组 List
        logger.info("行情列表 === " + quotationList.toString());
        logger.info("金交所行情数目 === " + expectRsLengh);
        logger.info("云 赢 行情数目 === " + quotationList.length());

        ArrayList<Double> realArraySingle = new ArrayList<Double>(); // 用来存放 每天的云赢行情
        int index = 0;             // 表示 金交所行情的下标
        double total_amount = 0.00;// 金交所 累计的 成交量
        for (int i = 0; i < quotationList.length(); i++) {

            // 将 JSONArray 转化为 ArrayList
            JSONArray quotationSingle = quotationList.getJSONArray(i);
            for (int j = 0; j < quotationSingle.length(); j++) {
                if (!quotationSingle.getString(j).equals("null")) {
                    realArraySingle.add(quotationSingle.getDouble(j));
                } else {
                    realArraySingle.add(0.0);
                }
            }

            Double yjt = 0.00;
            Double iwin = 0.00;

            // 如果 期待结果取完了，则不进来，避免 数组越界错误。
            if (index < expectRsLengh) {
                QuotationData quotationData = new QuotationData();
                quotationData.setDate(quotationSingle.getString(0));
                List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List
                boolean isError = false;
                for (int key = 0; key < fields.length(); key++) {
                    if (fields.getString(key).equals("last_px")) {
                        yjt = expectRs.getJSONObject(index).getDouble("f_new_price");
                        iwin = realArraySingle.get(key);
                        if (!((yjt - iwin <= deviation && yjt >= iwin) || (iwin - yjt <= deviation && iwin >= yjt))) {
                            ErrorData errorData = new ErrorData();
                            errorData.setErrorField(fields.getString(key));
                            errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_new_price"))));
                            errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                            errorDataList.add(errorData);
                            result = false;
                            isError = true;
                        }
                    }
                    if (fields.getString(key).equals("avg_px")) {
                        yjt = expectRs.getJSONObject(index).getDouble("f_avg_price");
                        iwin = realArraySingle.get(key);
                        if (!((yjt - iwin <= deviation && yjt >= iwin) || (iwin - yjt <= deviation && iwin >= yjt))) {
                            ErrorData errorData = new ErrorData();
                            errorData.setErrorField(fields.getString(key));
                            errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(index).get("f_avg_price"))));
                            errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                            errorDataList.add(errorData);
                            result = false;
                            isError = true;
                        }
                    }
                    if (fields.getString(key).equals("business_amount")) {
                        total_amount = total_amount + Double.valueOf(expectRs.getJSONObject(index).get("f_total").toString());// 金交所成交量累加
                        yjt = total_amount;
                        iwin = realArraySingle.get(key);
                        if (!((yjt - iwin <= deviation && yjt >= iwin) || (iwin - yjt <= deviation && iwin >= yjt))) {
                            ErrorData errorData = new ErrorData();
                            errorData.setErrorField(fields.getString(key));
                            errorData.seteValue(String.valueOf(total_amount));
                            errorData.setrValue(String.valueOf(realArraySingle.get(key)));
                            errorDataList.add(errorData);
                            result = false;
                            isError = true;
                        }
                    }
                }
                if (isError) {
                    quotationData.setErrorList(errorDataList);
                    errorResultList.add(quotationData);
                }
                index++;

            }
            realArraySingle.clear();  // 清空
        }

        logger.info("错误消息列表 === " + errorResultList.toString());
        trendData.setQuotationList(errorResultList);
        trendData.setDeviation(deviation);
        trendData.setErrTotal(errorResultList.size());
        trendData.setCmpTotal(quotationList.length());

        StringBuffer cmpField = new StringBuffer();
        for (int key = 0; key < fields.length(); key++) {
            if (!fields.getString(key).equals("min_time")) {
                cmpField.append(fields.getString(key));
                if (key < fields.length() - 1) {
                    cmpField.append(",");
                }
            }
        }
        trendData.setCmpField(cmpField.toString());

        NumberFormat numberFormat = NumberFormat.getInstance(); // 创建一个数值格式化对象
        numberFormat.setMaximumFractionDigits(2);               // 设置精确到小数点后2位
        String proportion = numberFormat.format((double) errorResultList.size() / (double) quotationList.length() * 100);
        trendData.setProportion(proportion + "%");

        return new BaseResp(result, trendData);
    }


    /**
     * 上海金 行情 比对
     *
     * @return
     */
    @RequestMapping(value = "/shau", method = RequestMethod.POST)
    public BaseResp comparaShau(@Valid Req700027 req) throws JSONException {
        Boolean result = true; // 用来返回 结果标志
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情

        JSONObject realRs = new JSONObject(realResult);
        JSONArray expectRs = new JSONArray(expectResult);// 金交所行情
        Integer expectRsLengh = expectRs.length();       // 金交所K线数量
        if (expectRsLengh == 0) {
            return new BaseResp(Boolean.FALSE, "金交所数据为空");
        }

        JSONObject realData = realRs.getJSONObject("data");
        List<String> keysList = new ArrayList<String>();
        Iterator<String> it = realData.keys();
        while (it.hasNext()) {
            keysList.add(it.next());// 获得key
        }
        JSONArray fields = realData.getJSONArray("fields");
        String code = "";       // 自己去 云赢行情里取 产品代码
        for (String key : keysList) {
            if (!key.equals("fields")) {
                code = key;
            }
        }
        logger.info("产品代码 === " + code);
        JSONArray quotationList = realData.getJSONArray(code);      // 云赢行情数组 List
        logger.info("行情列表 === " + quotationList.toString());
        logger.info("金交所行情数目 === " + expectRsLengh);
        logger.info("云 赢 行情数目 === " + quotationList.length());

        ArrayList<Double> realArraySingle = new ArrayList<Double>(); // 用来存放 每天的云赢行情
        Boolean isCompara = false; // 标志 是否比对过
        for (int i = 0; i < quotationList.length(); i++) {

            // 将 JSONArray 转化为 ArrayList
            JSONArray quotationSingle = quotationList.getJSONArray(i);
            for (int j = 0; j < quotationSingle.length(); j++) {
                if (quotationSingle.getString(j).equals("SHAU")) {
                    realArraySingle.add(0.0);
                } else if (!quotationSingle.getString(j).equals("null")) {
                    realArraySingle.add(quotationSingle.getDouble(j));
                } else {
                    realArraySingle.add(0.0);
                }
            }

            String rDate = quotationSingle.getString(0); // 云赢时间

            QuotationData quotationData = new QuotationData();
            quotationData.setDate(quotationSingle.getString(0));
            List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List
            Boolean isbijiao = false;
            // 早盘
            for (int k1 = 0; k1 < expectRsLengh; k1++) {
                // 判断日期 和 早盘
                if (rDate.equals(expectRs.getJSONObject(k1).get("f_exch_date").toString()) &&
                        expectRs.getJSONObject(k1).getString("f_game_id").equals("1")) {
                    isCompara = true; // 进来了说明 比对过了
                    isbijiao = true;
                    if (!Double.valueOf((Double) expectRs.getJSONObject(k1).get("f_fp_price")).equals(realArraySingle.get(2))) {
                        ErrorData errorData = new ErrorData();
                        errorData.setErrorField(fields.getString(2));
                        errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k1).get("f_fp_price"))));
                        errorData.setrValue(String.valueOf(realArraySingle.get(2)));
                        errorDataList.add(errorData);
                        result = false;
                    }
                    break;
                }
            }
            // 午盘
            for (int k2 = 0; k2 < expectRsLengh; k2++) {
                // 判断日期 和 午盘
                if (rDate.equals(expectRs.getJSONObject(k2).get("f_exch_date").toString()) &&
                        expectRs.getJSONObject(k2).getString("f_game_id").equals("2")) {
                    isCompara = true; // 进来了说明 比对过了
                    isbijiao = true;
                    if (!Double.valueOf((Double) expectRs.getJSONObject(k2).get("f_fp_price")).equals(realArraySingle.get(3))) {
                        ErrorData errorData = new ErrorData();
                        errorData.setErrorField(fields.getString(3));
                        errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(k2).get("f_fp_price"))));
                        errorData.setrValue(String.valueOf(realArraySingle.get(3)));
                        errorDataList.add(errorData);
                        result = false;
                    }
                    break;
                }
            }

            if (!isbijiao) {
                ErrorData errorData = new ErrorData();
                errorData.setErrorField("该日期没有对应金交所行情");
                errorDataList.add(errorData);
            }

            quotationData.setErrorList(errorDataList);
            errorResultList.add(quotationData);

            realArraySingle.clear();  // 清空
        }

        logger.info("错误消息列表 === " + errorResultList.toString());
        if (!isCompara) {
            return new BaseResp(Boolean.FALSE, "两者没有一致的日期，因此没有比对过！！！");
        }

        return new BaseResp(result, errorResultList);
    }


    /**
     * 最新日k 行情 比对
     *
     * @return
     */
    @RequestMapping(value = "/lastday", method = RequestMethod.POST)
    public BaseResp comparaLastday(@Valid Req700028 req) throws JSONException {

        Boolean result = true; // 用来返回 结果标志
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情
        String prodCode = req.getProdCode();        // 产品代码
        logger.info("产品代码 === " + prodCode);

        JSONObject realRs = new JSONObject(realResult);
        JSONArray expectRs = new JSONArray(expectResult);// 金交所行情
        Integer expectRsLengh = expectRs.length();       // 金交所数量
        if (expectRsLengh != 1) {
            return new BaseResp(Boolean.FALSE, "金交所数据只能传当天一条");
        }

        JSONObject realData = realRs.getJSONObject("data");
        JSONObject day = realData.getJSONObject("day");
        JSONArray fields = day.getJSONArray("fields");
        JSONArray quotationList = day.getJSONArray("lastdata");      // 行情数组 List
        logger.info("行情列表 === " + quotationList.toString());

        Boolean isCompara = false; // 标志 全部数据 是否比对过
        for (int i = 0; i < quotationList.length(); i++) {

            JSONArray quotationSingle = quotationList.getJSONArray(i);
            String rDate = quotationSingle.getString(0);                         // 云赢时间
            String eDate = expectRs.getJSONObject(0).get("f_date").toString();
            ;   // 金交所时间

            if (quotationSingle.getString(2).equals(prodCode) && rDate.equals(eDate)) {
                isCompara = true; // 进来说明 比对过了

                QuotationData quotationData = new QuotationData();
                quotationData.setDate(quotationSingle.getString(0));
                List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List

                if (!Double.valueOf((Double) expectRs.getJSONObject(0).get("f_open_price")).equals(quotationSingle.getDouble(3))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(3));
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(0).get("f_open_price"))));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(3)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (!Double.valueOf((Double) expectRs.getJSONObject(0).get("f_close_price")).equals(quotationSingle.getDouble(6))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(6));
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(0).get("f_close_price"))));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(6)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (!Double.valueOf((Double) expectRs.getJSONObject(0).get("f_max_price")).equals(quotationSingle.getDouble(4))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(4));
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(0).get("f_max_price"))));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(4)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (!Double.valueOf((Double) expectRs.getJSONObject(0).get("f_min_price")).equals(quotationSingle.getDouble(5))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(5));
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(0).get("f_min_price"))));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(5)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (!Double.valueOf(expectRs.getJSONObject(0).get("f_total").toString()).equals(quotationSingle.getDouble(7))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(7));
                    errorData.seteValue(String.valueOf(Double.valueOf(expectRs.getJSONObject(0).get("f_total").toString())));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(7)));
                    errorDataList.add(errorData);
                    result = false;
                }
                if (quotationSingle.getDouble(8) != (Double.valueOf((Double) expectRs.getJSONObject(0).get("f_money")) * 1000)) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(8));
                    errorData.seteValue(String.valueOf(Double.valueOf((Double) expectRs.getJSONObject(0).get("f_money")) * 1000));
                    errorData.setrValue(String.valueOf(quotationSingle.getDouble(8)));
                    errorDataList.add(errorData);
                    result = false;
                }

                quotationData.setErrorList(errorDataList);
                errorResultList.add(quotationData);
                break;
            }

        }

        logger.info("错误消息列表 === " + errorResultList.toString());

        if (!isCompara) {
            return new BaseResp(Boolean.FALSE, "两者没有一致的日期和产品，因此没有比对过！！！");
        }

        return new BaseResp(result, errorResultList);
    }


    /**
     * 行情快照 比对
     *
     * @return
     */
    @RequestMapping(value = "/real", method = RequestMethod.POST)
    public BaseResp comparaReal(@Valid Req700027 req) throws JSONException {

        Boolean result = true; // 用来返回 结果标志
        ArrayList<QuotationData> errorResultList = new ArrayList<QuotationData>();// 用来返回错误列表

        String expectResult = req.getExpectResult();// 金交所行情
        String realResult = req.getRealResult();    // 云赢行情

        JSONObject realRs = new JSONObject(realResult);  // 云赢行情
        JSONObject expectRs = new JSONObject(expectResult);// 金交所行情


        JSONObject realData = realRs.getJSONObject("data");
        JSONObject snapshot = realData.getJSONObject("snapshot");
        List<String> keysList = new ArrayList<String>();
        Iterator<String> it = snapshot.keys();
        while (it.hasNext()) {
            keysList.add(it.next());// 获得key
        }
        JSONArray fields = snapshot.getJSONArray("fields");
        String code = "";          // 自己去 云赢行情里取 产品代码
        for (String key : keysList) {
            if (!key.equals("fields")) {
                code = key;
            }
        }
        logger.info("keysList === " + keysList);
        logger.info("产品代码 === " + code);
        JSONArray quotationList = snapshot.getJSONArray(code);      // 行情数组 List
        logger.info("行情列表 === " + quotationList.toString());

        QuotationData quotationData = new QuotationData();
        quotationData.setDate(quotationList.getString(3)); // 存放时间戳
        List<ErrorData> errorDataList = new ArrayList<ErrorData>(); // 存放错误信息List

        // 云赢 需要比对的字段列表
        List<String> iwinComparaFieldList =
                Arrays.asList("last_px", "px_change", "px_change_rate", "open_px", "high_px"
                        , "low_px", "preclose_px", "business_amount", "business_balance", "avg_px"
                        , "up_px", "down_px", "settlement", "prev_settlement", "amount"
                        , "bid_amnt_i", "bid_price_i", "bid_amnt_o", "bid_price_o");

        // 金交所需要比对的字段列表
        List<String> yjtComparaFieldList =
                Arrays.asList("f_new_price", "f_charge_value", "f_charge_rate", "f_open_price", "f_max_price"
                        , "f_min_price", "f_prev_close_price", "f_total", "f_total_amount", "f_avg_price"
                        , "f_up_price", "f_down_price", "f_settle_price", "f_pre_settle_price", "f_position"
                        , "f_buy_count1", "f_buy_price1", "f_sell_count1", "f_sell_price1");


        for (int key = 0; key < fields.length(); key++) { // 遍历入参 传进来的字段

            for (int f = 0; f < iwinComparaFieldList.size(); f++) {  //对应字段去比对 f 代表同一个下标

                if (fields.getString(key).equals(iwinComparaFieldList.get(f))) {
                    if (!Double.valueOf(expectRs.getDouble(yjtComparaFieldList.get(f))).equals(quotationList.getDouble(key))) {
                        ErrorData errorData = new ErrorData();
                        errorData.setErrorField(fields.getString(key));
                        errorData.seteValue(String.valueOf(expectRs.getDouble(yjtComparaFieldList.get(f))));
                        errorData.setrValue(String.valueOf(quotationList.getDouble(key)));
                        errorDataList.add(errorData);
                        result = false;
                    }
                }

            }

            // 单独 把日增仓拿出来 判断
            if (fields.getString(key).equals("day_add_amount")) {
                if (!Double.valueOf(expectRs.getDouble("f_position") - expectRs.getDouble("f_pre_position")).equals(quotationList.getDouble(key))) {
                    ErrorData errorData = new ErrorData();
                    errorData.setErrorField(fields.getString(key));
                    errorData.seteValue(String.valueOf(expectRs.getDouble("f_position") - expectRs.getDouble("f_pre_position")));
                    errorData.setrValue(String.valueOf(quotationList.getDouble(key)));
                    errorDataList.add(errorData);
                    result = false;
                }
            }

        }
        quotationData.setErrorList(errorDataList);
        errorResultList.add(quotationData);

        logger.info("错误消息列表 === " + errorResultList.toString());

        return new BaseResp(result, errorResultList);
    }


    /**
     * 取几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static String getDateAfter(String d, Integer day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yMMddHHmm");
        Date currdate = format.parse(d);
        Calendar now = Calendar.getInstance();
        now.setTime(currdate);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        String enddate = format.format(now.getTime());
        return enddate;
    }


    public static void main(String[] args) throws JSONException, ParseException {

        String expectRs = "[{\"f_open_price\":287.49,\"f_close_price\":283.2,\"f_max_price\":288.0,\"f_min_price\":282.51,\"f_total\":8736468,\"f_money\":2.4866604E7,\"f_date\":20190111},{\"f_open_price\":283.49,\"f_close_price\":283.5,\"f_max_price\":284.25,\"f_min_price\":282.02,\"f_total\":7722770,\"f_money\":2.1844453E7,\"f_date\":20190118}]";
        String realRs = "{\"errorCode\":0,\"errorInfo\":\"SUCCESS\",\"data\":{\"candle\":{\"fields\":[\"min_time\",\"open_px\",\"high_px\",\"low_px\",\"close_px\",\"business_amount\",\"business_balance\"],\"Au99.99.SGEX\":[[20181221,510.62,1006.42,510.0,1000.0,984638,96983128020000],[20181226,700.0,1622.0,611.47,646.0,2230158,153930538520000],[20190104,283.98,289.3,283.98,287.5,41549751,1188320499765171],[20190111,287.49,288.0,282.51,283.2,1939106,24866605130],[20190118,283.49,284.25,282.02,283.5,6732210,19042423910]]}}}";
        String type = "2";

        ComparaController compara = new ComparaController();
        Req700021 req700021 = new Req700021();
        req700021.setExpectResult(expectRs);
        req700021.setRealResult(realRs);
        req700021.setType(type);
        //compara.comparaKLine(req700021);
        System.out.println(getDateAfter("901181139", 3));

    }

}
