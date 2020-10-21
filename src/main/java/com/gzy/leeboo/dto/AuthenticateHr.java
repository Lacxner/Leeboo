package com.gzy.leeboo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 只包含认证授权属性的Hr对象，用户创建新Hr
 */
public class AuthenticateHr implements Serializable {
    private static final long serialVersionUID = 8983264826103323141L;

    private Integer id;
    @NotNull
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{2,12}$", message = "HR姓名格式不正确！")
    private String name;
    @NotNull
    @Pattern(regexp = "^\\w{4,12}$", message = "用户名格式不正确！")
    private String username;
    @NotNull
    @Pattern(regexp = "^\\w{4,12}$", message = "密码格式不正确！")
    private String password;
    @NotNull
    @Length(min = 1, max = 30)
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
