package com.gzy.leeboo.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用于更新Hr所拥有的角色
 */
public class UpdateHrRole implements Serializable {
    private static final long serialVersionUID = 2227425632811943964L;

    @NotNull
    @Min(1)
    private Integer hrId;
    @Valid
    private List<Integer> roleIds;

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
