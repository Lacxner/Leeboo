package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 惩罚
 */
public class Punishment implements Serializable {
    private static final long serialVersionUID = -7381669093018218716L;

    private Integer id;
    private String name;
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
