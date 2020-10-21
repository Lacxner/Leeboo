package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validator.group.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 职位
 */
public class Position implements Serializable {
    private static final long serialVersionUID = -8410283570553143765L;

    @NotNull(groups = {AddEmployee.class, UpdateMoveEmployee.class, UpdateName.class, UpdateEnabled.class})
    @Min(value = 1, groups = {AddEmployee.class, UpdateMoveEmployee.class, UpdateName.class, UpdateEnabled.class})
    private Integer id;
    @NotNull(groups = {UpdateName.class, Add.class})
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{2,12}$", groups = {UpdateName.class, Add.class}, message = "职位名称格式不正确！")
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