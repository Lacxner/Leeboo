package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validation.groups.Add;
import com.gzy.leeboo.config.validation.groups.Update;
import com.gzy.leeboo.config.validation.groups.UpdateEnabled;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 职称
 */
public class Rank implements Serializable {
    private static final long serialVersionUID = 1863737572711570327L;

    @NotNull(groups = {Update.class, UpdateEnabled.class})
    @Min(value = 1, groups = {Update.class, UpdateEnabled.class})
    private Integer id;

    @NotNull(groups = {Update.class, Add.class})
    @Length(min = 2, max = 12, groups = {Update.class, Add.class})
    private String name;

    @NotNull(groups = {Update.class, Add.class})
    private String level;

    @NotNull(groups = UpdateEnabled.class)
    private Boolean enabled;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

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
        this.name = name == null ? null : name.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}