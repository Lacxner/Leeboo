package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validator.group.Add;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工资账套
 */
public class SalarySob implements Serializable {
    private static final long serialVersionUID = -1000267715565480861L;

    @NotNull(groups = Add.class)
    @Min(value = 1, groups = Add.class)
    private Integer id;
    @NotNull
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{2,12}$", message = "工资账套名称格式不正确！")
    private String name;
    @NotNull
    @Min(0)
    private Integer basicSalary;
    @NotNull
    @Min(0)
    private Integer lunchSalary;
    @NotNull
    @Min(0)
    private Integer trafficSalary;
    private Double allSalary;
    @NotNull
    @Min(0)
    private Integer pensionBase;
    @NotNull
    @Min(0)
    private Double pensionPer;
    @NotNull
    @Min(0)
    private Integer medicalBase;
    @NotNull
    @Min(0)
    private Double medicalPer;
    @NotNull
    @Min(0)
    private Integer accumulationFundBase;
    @NotNull
    @Min(0)
    private Double accumulationFundPer;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime;

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

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getLunchSalary() {
        return lunchSalary;
    }

    public void setLunchSalary(Integer lunchSalary) {
        this.lunchSalary = lunchSalary;
    }

    public Integer getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(Integer trafficSalary) {
        this.trafficSalary = trafficSalary;
    }

    public Double getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Double allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Integer pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Double getPensionPer() {
        return pensionPer;
    }

    public void setPensionPer(Double pensionPer) {
        this.pensionPer = pensionPer;
    }

    public Integer getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Integer medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Double getMedicalPer() {
        return medicalPer;
    }

    public void setMedicalPer(Double medicalPer) {
        this.medicalPer = medicalPer;
    }

    public Integer getAccumulationFundBase() {
        return accumulationFundBase;
    }

    public void setAccumulationFundBase(Integer accumulationFundBase) {
        this.accumulationFundBase = accumulationFundBase;
    }

    public Double getAccumulationFundPer() {
        return accumulationFundPer;
    }

    public void setAccumulationFundPer(Double accumulationFundPer) {
        this.accumulationFundPer = accumulationFundPer;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}