package springboot.hello.hellospringboot.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.common.utils.SignKeyGenerator;
import springboot.hello.hellospringboot.request.Req800001;
import springboot.hello.hellospringboot.response.BaseResp;

/**
 * <p>
 *  获取MD5加签 签名
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@RestController
@RequestMapping("/signature")
public class SignatureController {
    private static Logger logger = LoggerFactory.getLogger(SignatureController.class);

    @Value("${shakey}")
    private String shakey;

    /**
     * 获取签名
     * @param request
     * @param response
     * HttpServletRequest request,Req800001 req, HttpServletResponse response
     * @return
     */
    @RequestMapping(value = "/getSign", method = org.springframework.web.bind.annotation.RequestMethod.POST)
    public BaseResp valideSignature(HttpServletRequest request, HttpServletResponse response) {
        try {
            /*if (req == null){
                return new BaseResp(Boolean.FALSE, "没有参数");
            }*/
            Map<String, Object> signMap = new HashMap();

            /*String[] fields = { "user_id", "login_account", "login_ticket", "client_uuid", "version", "client" ,
                    "product_id", "client_token", "user_code", "goods_id", "entrust_price", "used_price_flag" ,
                    "timestamp", "zl_user_id", "user_name_text", "cert_type", "cert_id", "purchase_type" ,
                    "bank_code", "card_no", "pay_password", "fund_user_id", "order_no", "pay_way" ,
                    "service_type", "goods_type", "nonceStr", "grant_type", "present_unit", "order_type" ,
                    "pay_status"
            };*/

            Enumeration<String> parameterNames = request.getParameterNames();
            logger.info(">>>>>>>>>>>>>>>>>入参参数列表：" + parameterNames);

            for(Enumeration e=parameterNames;e.hasMoreElements();){
                String thisName=e.nextElement().toString();
                String thisValue=request.getParameter(thisName);
                logger.info(">>>>>>>>>>>>>>参数：" + thisName +">>>>>>>>>>>>>>值："+thisValue);
                if(!thisValue.equals("")||thisValue == null){
                    signMap.put(thisName, thisValue);
                }else{
                    return new BaseResp(Boolean.FALSE,thisName+" 不能为空");
                }
            }

            String mySignature = SignKeyGenerator.getSign(signMap, shakey);

            return new BaseResp(Boolean.TRUE, "mySignature: "+mySignature);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new BaseResp(Boolean.FALSE,"获取失败");
        }
    }

}

