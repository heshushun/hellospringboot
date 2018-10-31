package springboot.hello.hellospringboot.common.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hss
 * @Date: 2018/10/30
 * @Desc: jwt 生成 token
 */
public class JwtHelper {

    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * 默认签名
     */
    public static final String SECRET = "hellospringboot";

    /**
     * 默认有效时间 一天
     */
    public static final long TIME_OUT= 24*60*60L;


    /**
     * 通过 Token 和 签名SECRET 去获取身份信息
     * @param jsonWebToken
     * @param secret
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String secret){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * 通过 身份信息 和 签名SECRET 生成token
     * @param seconds
     * @param secret
     * @param params
     * @return
     */
    public static String createJWT(Long seconds, String secret, Map<String,? extends Object> params){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = secret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT").signWith(signatureAlgorithm,signingKey);
        if(params != null && !params.isEmpty()) {
            params.forEach((k,v) -> builder.claim(k,v));
        }
        if (seconds >= 0) {
            long expMillis = nowMillis + seconds * 1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        return builder.compact();
    }



    public static void main(String[] args) {
        String token = createJWT(TIME_OUT,SECRET,new HashMap<String, Object>(){{
            put("customerId","121");
            put("backURL","http://www.baidu.com");
        }});
        System.out.printf(token+"\n");
        Claims claims = parseJWT(token, SECRET);
        System.out.println(claims);
    }
}
