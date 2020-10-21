package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户的角色
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -2625543786186471132L;

    @NotNull(groups = Update.class)
    @Min(value = 1, groups = Update.class)
    private Integer id;

    @NotNull(groups = {Update.class, Add.class})
    @Length(min = 2, max = 12, groups = {Update.class, Add.class})
    private String name;

    @NotNull(groups = {Update.class, Add.class})
    @Length(min = 2, max = 12, groups = {Update.class, Add.class})
    private String nameZh;

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
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}