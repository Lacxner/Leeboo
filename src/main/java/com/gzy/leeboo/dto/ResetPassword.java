package com.gzy.leeboo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 重设密码时的数据
 */
public class ResetPassword implements Serializable {
    private static final long serialVersionUID = -1526481292895018201L;

    @NotNull
    @Min(1)
    private Integer id;
    @NotNull
    @Pattern(regexp = "^\\w{4,12}$", message = "密码格式不正确！")
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
