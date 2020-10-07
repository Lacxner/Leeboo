package com.gzy.leeboo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 只包含基础属性的菜单对象
 */
public class BasicMenu implements Serializable {
    private static final long serialVersionUID = 7192221949704476567L;

    private Integer id;
    private String name;
    private List<BasicMenu> children;

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

    public List<BasicMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BasicMenu> children) {
        this.children = children;
    }
}
