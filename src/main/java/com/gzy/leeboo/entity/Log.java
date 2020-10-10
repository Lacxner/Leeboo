package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志
 */
public class Log implements Serializable {
    private static final long serialVersionUID = 48960034333590793L;

    private Integer id;
    private String method;
    private String pattern;
    private String url;
    private String username;
    private String ip;
    @JsonFormat(pattern = "yyyy年MM月dd月 HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime visitTime;
    private Long executionTime;

    public Log() {}

    public Log(String method, String pattern, String url, String username, String ip, LocalDateTime visitTime, Long executionTime) {
        this.method = method;
        this.pattern = pattern;
        this.url = url;
        this.username = username;
        this.ip = ip;
        this.visitTime = visitTime;
        this.executionTime = executionTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }
}
