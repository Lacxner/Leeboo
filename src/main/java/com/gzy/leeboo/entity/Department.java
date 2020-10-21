package com.gzy.leeboo.entity;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.AddEmployee;
import com.gzy.leeboo.config.validator.group.Update;
import com.gzy.leeboo.config.validator.group.UpdateMoveEmployee;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * 部门
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 8399612316276452597L;

    @NotNull(groups = {AddEmployee.class, UpdateMoveEmployee.class})
    @Min(value = 1, groups = {AddEmployee.class, UpdateMoveEmployee.class})
    private Integer id;
    @NotNull(groups = Add.class)
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{2,16}$", groups = Add.class, message = "部门名称格式不正确！")
    private String name;
    @NotNull(groups = Add.class)
    @Min(value = -1, groups = Add.class)
    private Integer parentId;
    private String path;
    private Boolean enabled;
    private Boolean isParent;
    @Valid
    private List<Department> children;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}