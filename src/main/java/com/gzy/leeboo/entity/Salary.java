package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工资
 */
public class Salary implements Serializable {
    private static final long serialVersionUID = 5739597335331010435L;

    private Integer id;
    private Integer basicSalary;
    private Integer reward;
    private Integer punishment;
    private Integer lunchSalary;
    private Integer trafficSalary;
    private Double allSalary;
    private Integer pensionBase;
    private Double pensionPer;
    private Integer medicalBase;
    private Double medicalPer;
    private Integer accumulationFundBase;
    private Double accumulationFundPer;
    private Double actualSalary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getPunishment() {
        return punishment;
    }

    public void setPunishment(Integer punishment) {
        this.punishment = punishment;
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

    public Double getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(Double actualSalary) {
        this.actualSalary = actualSalary;
    }
}