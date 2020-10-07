package com.gzy.leeboo.dto;

import java.io.Serializable;

/**
 * 聊天界面的Hr信息
 */
public class ChatHr implements Serializable {
    private static final long serialVersionUID = -7710820335970399163L;

    private Integer id;
    private String name;
    private String username;
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
