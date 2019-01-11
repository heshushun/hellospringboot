package springboot.hello.hellospringboot.common.config;

import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import springboot.hello.hellospringboot.common.helper.JwtHelper;
import springboot.hello.hellospringboot.common.utils.AccountUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2018/12/12.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/ws/endpointChat").setHandshakeHandler(new  DefaultHandshakeHandler(){
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                // key就是服务器和客户端保持一致的标记，一般可以用账户名称，或者是用户ID。
                // 获取登录后的账号
                HttpHeaders httpHeaders = request.getHeaders();
                String Cookie =  httpHeaders.get("Cookie").get(0);
                if (Cookie.indexOf("Admin-Token")==-1){
                    return new MyPrincipal("test");
                }else {
                    String authorization = Cookie.substring(Cookie.lastIndexOf("=") + 1, Cookie.length());
                    Claims claims = JwtHelper.parseJWT(authorization, JwtHelper.SECRET);//通过 token 和 签名SECRET 去获取身份信息
                    String account = (String) claims.get("account");
                    return new MyPrincipal(account);
                }
            }
        }).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue","/topic");
    }

    /**
     * 自定义的Principal
     */
    class MyPrincipal implements Principal{
        private String key;
        public MyPrincipal(String key) {
            this.key = key;
        }
        @Override
        public String getName() {
            return key;
        }
    }

}
