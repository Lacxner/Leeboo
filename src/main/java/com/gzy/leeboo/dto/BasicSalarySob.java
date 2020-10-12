package com.gzy.leeboo.dto;

import java.io.Serializable;

/**
 * 用于下拉框中选择工资账套
 */
public class BasicSalarySob implements Serializable {
    private static final long serialVersionUID = -4588975534091928672L;

    private Integer id;
    private String name;

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
}
