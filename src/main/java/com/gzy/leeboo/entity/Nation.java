package com.gzy.leeboo.entity;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.AddEmployee;
import com.gzy.leeboo.config.validator.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 民族
 */
public class Nation implements Serializable {
    private static final long serialVersionUID = -2791693872200671178L;

    @NotNull(groups = AddEmployee.class)
    @Min(value = 1, groups = { AddEmployee.class, Update.class })
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