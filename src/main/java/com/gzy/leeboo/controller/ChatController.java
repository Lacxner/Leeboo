package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.ChatMessage;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.service.HrService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ChatController {
    private HrService hrService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
    }

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/getAllHrsWithoutMyself")
    public Result getAllHrsWithoutMyself() {
        // 获取当前Hr
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Hr) {
                Hr hr = (Hr) principal;
                return Result.success().data("items", hrService.getAllHrsWithoutMyself(hr.getId()));
            }
        }
        return Result.failure().code(401).message("您尚未登录！");
    }

    /**
     * 处理 WebSocket 的消息请求
     * @param authentication 如果 Authentication 作为 Controller 中的方法参数，则 Spring Security 会自动为其赋值，Principal 同理
     * @param chatMessage 聊天消息的相关信息
     */
    @MessageMapping("/ws/chat")
    public void handleMessage(Authentication authentication, ChatMessage chatMessage) {
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Hr) {
                Hr hr = (Hr) principal;
                chatMessage.setFrom(hr.getUsername());
                chatMessage.setName(hr.getName());
            }
        }
        chatMessage.setMessageTime(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(), "/queue/chat", chatMessage);
    }
}
