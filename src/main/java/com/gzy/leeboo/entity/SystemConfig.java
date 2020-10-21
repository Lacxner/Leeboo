package com.gzy.leeboo.entity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统基本信息
 */
public class SystemConfig implements Serializable {
    private static final long serialVersionUID = -1944152984494710866L;

    private Integer id;
    @NotEmpty
    private String version;
    @NotNull
    @Pattern(regexp = "^[\\w（）\\u4e00-\\u9fa5]{4,18}$", message = "公司名称格式不正确！")
    private String company;
    @Valid
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
