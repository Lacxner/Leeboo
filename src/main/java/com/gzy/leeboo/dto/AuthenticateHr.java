package com.gzy.leeboo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 只包含认证授权属性的Hr对象，用户创建新Hr
 */
public class AuthenticateHr implements Serializable {
    private static final long serialVersionUID = 8983264826103323141L;

    private Integer id;

    @NotNull
    @Length
    private String name;
    private String username;
    private String password;
    private String remark;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
