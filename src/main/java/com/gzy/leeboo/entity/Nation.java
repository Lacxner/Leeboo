package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 民族
 */
public class Nation implements Serializable {
    private static final long serialVersionUID = -2791693872200671178L;

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