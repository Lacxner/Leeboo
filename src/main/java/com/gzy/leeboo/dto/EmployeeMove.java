package com.gzy.leeboo.dto;

import com.gzy.leeboo.entity.Department;
import com.gzy.leeboo.entity.Position;
import com.gzy.leeboo.entity.Rank;

import java.io.Serializable;

/**
 * 员工的相关信息，包括部门、职位和职称
 */
public class EmployeeMove implements Serializable {
    private static final long serialVersionUID = -215187957532470598L;

    private Integer id;
    private Department department;
    private Rank rank;
    private Position position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
