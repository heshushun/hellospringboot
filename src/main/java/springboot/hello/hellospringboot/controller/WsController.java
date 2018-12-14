package springboot.hello.hellospringboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import springboot.hello.hellospringboot.response.ChatResp;

import java.security.Principal;

/**
 * WebSocket 消息处理类
 * Created by sang on 2018/12/12.
 */
@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    /*@MessageMapping("/ws/chat")
    public void handleChat( String msg) {
        String fromUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String msg2 = msg.substring(0, msg.lastIndexOf(";"));

        String destUser = msg2.substring(msg2.lastIndexOf(";") + 1, msg2.length());
        String message = msg2.substring(0, msg2.lastIndexOf(";"));

        ChatResp chatResp = new ChatResp(message, fromUser);
        messagingTemplate.convertAndSendToUser(destUser, "/queue/chat", chatResp);
    }*/

    @MessageMapping("/ws/chat")
    public void handleChat(Principal principal, String msg) {
        String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String message = msg.substring(0, msg.lastIndexOf(";"));
        ChatResp chatResp = new ChatResp(message, principal.getName());
        messagingTemplate.convertAndSendToUser(destUser, "/queue/chat", chatResp);
    }
}
