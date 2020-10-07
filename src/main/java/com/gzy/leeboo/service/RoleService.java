package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.UpdateHrRole;
import com.gzy.leeboo.entity.Role;
import com.gzy.leeboo.mapper.MenuMapper;
import com.gzy.leeboo.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "role")
@Service
public class RoleService {
    private RoleMapper roleMapper;
    private MenuMapper menuMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Cacheable(key = "'allRoles'")
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRoles'")
    public Boolean addRoleWithMenu(Role role, List<Integer> menuIds) {
        if (!roleMapper.addRole(role) | !menuMapper.addRoleMenu(menuIds, role.getId())) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(cacheNames = "hr", key = "'allHrs'")
    public Boolean updateHrRole(UpdateHrRole updateHrRole) {
        roleMapper.deleteAllHrRolesByHrId(updateHrRole.getHrId());
        if (!updateHrRole.getRoleIds().isEmpty()) {
            if (!roleMapper.addHrRole(updateHrRole)) {
                return false;
            }
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "menu", key = "'allMenuWithRoles'"),
                    @CacheEvict(key = "'allRoles'")
            }
    )
    public Boolean updateRoleWithMenu(Role role, List<Integer> menuIds) {
        if (!roleMapper.updateRole(role) | !menuMapper.deleteAllMenuByRoleId(role.getId()) | !menuMapper.addRoleMenu(menuIds, role.getId())) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "menu", key = "'allMenuWithRoles'"),
                    @CacheEvict(key = "'allRoles'")
            }
    )
    public Boolean deleteRoleById(Integer id) {
        if (!roleMapper.deleteRoleById(id) | !menuMapper.deleteAllMenuByRoleId(id)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "menu", key = "'allMenuWithRoles'"),
                    @CacheEvict(key = "'allRoles'")
            }
    )
    public Boolean deleteBatchRoleByIds(List<Integer> ids) {
        if (!roleMapper.deleteBatchRoleByIds(ids) | !menuMapper.deleteBatchAllMenuByRoleIds(ids)) {
            return false;
        }
        return true;
    }
}
