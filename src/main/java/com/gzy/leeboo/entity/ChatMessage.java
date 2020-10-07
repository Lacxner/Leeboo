package com.gzy.leeboo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息的基本信息
 */
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -5155483600211284746L;

    private String from;
    private String to;
    private String content;
    private String name;
    private LocalDateTime messageTime;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }
}
