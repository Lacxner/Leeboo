package com.gzy.leeboo.dto;

import com.gzy.leeboo.entity.Department;
import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.entity.SalarySob;

import java.io.Serializable;

/**
 * 员工基本信息及其工资账套
 */
public class EmployeeSalarySob implements Serializable {
    private static final long serialVersionUID = 8625041936474213517L;

    private Integer id;
    private String name;
    private String workID;
    private String email;
    private String phone;
    private Department department;
    private SalarySob salarySob;

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

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public SalarySob getSalarySob() {
        return salarySob;
    }

    public void setSalarySob(SalarySob salarySob) {
        this.salarySob = salarySob;
    }
}
