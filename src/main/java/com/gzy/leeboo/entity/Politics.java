package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 政治面貌
 */
public class Politics implements Serializable {
    private static final long serialVersionUID = -7895655658178562833L;

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