package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validation.groups.Add;
import com.gzy.leeboo.config.validation.groups.UpdateEnabled;
import com.gzy.leeboo.config.validation.groups.UpdateName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 职位
 */
public class Position implements Serializable {
    private static final long serialVersionUID = -8410283570553143765L;

    @NotNull(groups = {UpdateName.class, UpdateEnabled.class})
    @Min(value = 1, groups = {UpdateName.class, UpdateEnabled.class})
    private Integer id;

    @NotNull(groups = {UpdateName.class, Add.class})
    @Length(min = 2, max = 16, groups = {UpdateName.class, Add.class})
    private String name;

    @NotNull(groups = UpdateEnabled.class)
    private Boolean enabled;

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
        this.name = name.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}