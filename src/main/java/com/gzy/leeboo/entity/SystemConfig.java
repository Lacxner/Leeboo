package com.gzy.leeboo.entity;

import java.io.Serializable;

/**
 * 系统基本信息
 */
public class SystemConfig implements Serializable {
    private static final long serialVersionUID = -1944152984494710866L;

    private Integer id;
    private String version;
    private String company;
    private Notice notice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
