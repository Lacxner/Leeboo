package com.gzy.leeboo.dto;

import java.io.Serializable;

/**
 * 重设密码时的数据
 */
public class ResetPassword implements Serializable {
    private static final long serialVersionUID = -1526481292895018201L;

    private Integer id;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
