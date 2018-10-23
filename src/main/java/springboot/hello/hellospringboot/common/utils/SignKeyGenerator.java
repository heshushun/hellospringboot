package springboot.hello.hellospringboot.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *  获取MD5加签 签名
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
public class SignKeyGenerator {
    private static Logger logger = LoggerFactory.getLogger(SignKeyGenerator.class);

    /**
     * 获取MD5加签 签名
     */
    public static String getSign(Map<String, Object> map, String shakey) {
        String result = "";
        try {

            List<Map.Entry<String, Object>> infoIds = new ArrayList(map.entrySet());

            List<String> infoStrings = new ArrayList();
            for (Map.Entry<String, Object> item : infoIds) {
                if (StringUtils.isNotEmpty((CharSequence)item.getKey())) {
                    String key = (String)item.getKey();
                    String val = (String)item.getValue();
                    if (StringUtils.isNotEmpty(val)) {
                        infoStrings.add(key + ":" + val);
                    }
                }
            }
            Collections.sort(infoStrings);

            StringBuilder sb = new StringBuilder();
            for(String info : infoStrings){
                sb.append(info + ":");
            }
            result = sb.toString() + shakey;
            logger.info(">>>>>>>>>>>>>>>>>校验的参数列表：" + sb.toString());

            result = DigestUtils.md5Hex(result);
        }
        catch (Exception e) {
            return null;
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>mySignature: " + result);
        return result;
    }


}

