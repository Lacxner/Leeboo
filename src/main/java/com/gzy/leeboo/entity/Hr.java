package com.gzy.leeboo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzy.leeboo.config.validator.annotation.Phone;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * HR用户
 */
public class Hr implements UserDetails, Serializable {
    private static final long serialVersionUID = 5652835615501269325L;

    @NotNull
    @Min(1)
    private Integer id;
    @NotNull
    @Pattern(regexp = "^[\\w_-（）\\u4e00-\\u9fa5]{2,12}$", message = "HR姓名格式不正确！")
    private String name;
    @NotNull
    @Phone(message = "HR手机号码格式不正确！")
    private String phone;
    @NotNull
    @Pattern(regexp = "^\\d{5,16}$", message = "QQ号码格式不正确！")
    private String qq;
    @NotNull
    @Pattern(regexp = "^[\\w（）\\u4e00-\\u9fa5]{4,24}$", message = "HR地址格式不正确！")
    private String address;
    private Boolean enabled;
    private String username;
    private String password;
    private String avatar;
    @NotNull
    @Length(min = 1, max = 30)
    private String remark;
    private List<Role> roles;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hr hr = (Hr) o;
        return Objects.equals(username, hr.username) && Objects.equals(password, hr.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
