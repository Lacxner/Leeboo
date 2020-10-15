package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 综合统计信息
 */
public class Statistics implements Serializable {
    private static final long serialVersionUID = -1744087222005949470L;

    private Integer id;
    private Integer totalEmp;
    private Integer newEmp;
    private Integer totalDep;
    private Double avgSalary;

    public Statistics() {}

    public Statistics(Integer totalEmp, Integer newEmp, Integer totalDep, Double avgSalary) {
        this.totalEmp = totalEmp;
        this.newEmp = newEmp;
        this.totalDep = totalDep;
        this.avgSalary = avgSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalEmp() {
        return totalEmp;
    }

    public void setTotalEmp(Integer totalEmp) {
        this.totalEmp = totalEmp;
    }

    public Integer getNewEmp() {
        return newEmp;
    }

    public void setNewEmp(Integer newEmp) {
        this.newEmp = newEmp;
    }

    public Integer getTotalDep() {
        return totalDep;
    }

    public void setTotalDep(Integer totalDep) {
        this.totalDep = totalDep;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }
}
