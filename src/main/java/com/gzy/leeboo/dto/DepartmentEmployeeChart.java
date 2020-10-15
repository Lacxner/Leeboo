package com.gzy.leeboo.dto;

import java.io.Serializable;

/**
 * 用于构建部门的员工统计图表数据
 */
public class DepartmentEmployeeChart implements Serializable {
    private static final long serialVersionUID = 4764220675084446108L;

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
