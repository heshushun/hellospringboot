package springboot.hello.hellospringboot.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springboot.hello.hellospringboot.common.utils.MD5Utils;
import springboot.hello.hellospringboot.common.utils.SignKeyGenerator;
import springboot.hello.hellospringboot.request.Req700008;
import springboot.hello.hellospringboot.request.Req700012;
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
     * @return
     */
    @RequestMapping(value = "/getSign", method = org.springframework.web.bind.annotation.RequestMethod.POST)
    public BaseResp valideSignature(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> signMap = new HashMap();

            Enumeration<String> parameterNames = request.getParameterNames();

            if (!parameterNames.hasMoreElements()){
                return new BaseResp(Boolean.FALSE, "没有参数");
            }

            ArrayList<String> parameterNamelist = new ArrayList<>();//参数名列表

            for(Enumeration e=parameterNames;e.hasMoreElements();){
                String thisName=e.nextElement().toString();
                String thisValue=request.getParameter(thisName);
                logger.info(">>>>>>>>>>>>>>参数：" + thisName +">>>>>>>>>>>>>>值："+thisValue);
                if(StringUtils.isNotEmpty(thisValue)){
                    signMap.put(thisName, thisValue);
                    parameterNamelist.add(thisName);
                }else{
                    return new BaseResp(Boolean.FALSE,thisName+" 不能为空");
                }
            }
            logger.info(">>>>>>>>>>>>>>>>>入参key列表：" + parameterNamelist);
            String mySignature = SignKeyGenerator.getSign(signMap, shakey);

            return new BaseResp(Boolean.TRUE, "mySignature: "+mySignature);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new BaseResp(Boolean.FALSE,"获取失败");
        }
    }



    /**
     * 获取MD5加密后的 签名
     * @return
     */
    @RequestMapping(value = "/getSignature" ,method = RequestMethod.POST)
    public BaseResp getSignature(@Valid Req700012 req){
        try {

            logger.info(">>>>>>>>>>>>>>>>>入参值：" + req.toString());

            //String inputStr = req.getBranchCode()+req.getChannel()+req.getSecretKey()+req.getDeviceId()+req.getTimestamp();//加密原值
            String inputStr = req.getChannel()+req.getSecretKey()+req.getDeviceId()+req.getTimestamp();//加密原值
            logger.info(">>>>>>>>>>>>>>>>>加密前原值：" + inputStr);

            MD5Utils md5Utils = new MD5Utils();

            String outputStr = md5Utils.md5(inputStr).toUpperCase();//生成加密密值
            logger.info(">>>>>>>>>>>>>>>>>加密后的值：" + outputStr);

            return new BaseResp(Boolean.TRUE, outputStr);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new BaseResp(Boolean.FALSE,"获取失败");
        }

    }

}

