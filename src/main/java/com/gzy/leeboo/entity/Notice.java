package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 通知
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = -1524487052444283237L;

    private Integer id;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
