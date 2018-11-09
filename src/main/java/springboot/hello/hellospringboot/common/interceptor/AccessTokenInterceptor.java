package springboot.hello.hellospringboot.common.interceptor;


import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springboot.hello.hellospringboot.common.exception.BizException;
import springboot.hello.hellospringboot.common.exception.builder.ErrorBuilder;
import springboot.hello.hellospringboot.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hss
 * @Date: 2018/10/30
 * @Desc: token 身份校验 过滤器
 */
public class AccessTokenInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_TYPE_MANAGER= "1";//后台

    private static final String AUTHORIZATION_TYPE_FRONT= "2";//前端

    /**
     * ant 风格 解析匹配工具类
     */
    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        if (isMatchExcludeUri(request)) {
            return Boolean.TRUE;
        }
        if("OPTIONS".equals(request.getMethod())) {
            return Boolean.TRUE;
        }

        String authorization = request.getHeader("Authorization");//token校验
        String authorizationType = request.getHeader("AuthorizationType");//token类型 1表示的是后台，2表示的是前端
        if(StringUtils.isBlank(authorizationType) || StringUtils.isBlank(authorization)) {
            throw new BizException(ErrorBuilder.buildBizError(8888,"8888","非法访问"));
        }

        //后台处理
        if(AUTHORIZATION_TYPE_MANAGER.equals(authorizationType)) {
            Claims claims = JwtHelper.parseJWT(authorization, JwtHelper.SECRET);//通过 token 和 签名SECRET 去获取身份信息
            if(claims==null){//解析认证信息错误抛异常
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "GET, POST");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
                throw new BizException(ErrorBuilder.buildBizError(8888,"8888","认证信息错误"));
            }
            String account = (String) claims.get("account");
            //if(StringUtils.isNotBlank(account) && "admin".equals(account)) {
            if(StringUtils.isNotBlank(account)) {
                return Boolean.TRUE;
            }
        //前端处理
        }else if(AUTHORIZATION_TYPE_FRONT.equals(authorizationType)){
            //前端token检验先不做
            return Boolean.TRUE;
        } else {
            throw new BizException(ErrorBuilder.buildBizError(8888,"8888","非法访问"));
        }

        return false;
    }

    /**
     * 匹配是否过滤的页面（如果是 登录页面 和 404 页面 就不要身份认证）
     * @param request
     * @return
     */
    private boolean isMatchExcludeUri(HttpServletRequest request) {
        String uri = request.getServletPath();
        List<String> list = new ArrayList();
        list.add("/user/login");
        list.add("/quartz/jobs");
        list.add("/quartz/triggers");
        list.add("/quartz/task/init");
        list.add("/quartz/task/pauseJob");
        list.add("/quartz/task/resumeJob");
        list.add("/quartz/task/deleteJob");
        list.add("/quartz/task/getJobStatus");
        list.add("/signature/getSign");
        list.add("/signature/getSignature");
        list.add("/user/get");
        Long count = list.stream().filter(u -> matcher.match(u, uri)).count();
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
