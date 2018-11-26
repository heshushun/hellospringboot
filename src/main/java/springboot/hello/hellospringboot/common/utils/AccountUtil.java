package springboot.hello.hellospringboot.common.utils;

import io.jsonwebtoken.Claims;
import springboot.hello.hellospringboot.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 从token中获取 当前登录的账号 工具
 */
public class AccountUtil {

    public String getLoginAccount (HttpServletRequest request){
        // 获取登录后的账号
        String authorization = request.getHeader("Authorization");//token校验
        Claims claims = JwtHelper.parseJWT(authorization, JwtHelper.SECRET);//通过 token 和 签名SECRET 去获取身份信息
        if(claims==null) {
            return "";
        }
        String account = (String) claims.get("account");
        return account;
    }

}
