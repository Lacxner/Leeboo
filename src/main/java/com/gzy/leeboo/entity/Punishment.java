package com.gzy.leeboo.entity;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.Delete;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 处罚
 */
public class Punishment implements Serializable {
    private static final long serialVersionUID = -7381669093018218716L;

    @NotNull(groups = Delete.class)
    @Min(value = 1, groups = Delete.class)
    private Integer id;
    @NotNull(groups = { Add.class, Delete.class })
    @Pattern(regexp = "^[\\w（）\\u4e00-\\u9fa5]{2,8}$", groups = { Add.class, Delete.class }, message = "处罚名称格式不正确！")
    private String name;
    @NotNull(groups = { Add.class, Delete.class })
    @Min(value = 1, groups = { Add.class, Delete.class })
    private Integer money;

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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
