package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.BasicMenu;
import com.gzy.leeboo.entity.Menu;
import com.gzy.leeboo.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "menu")
@Service
public class MenuService {
    private MenuMapper menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    public List<Menu> getAllMenuByHrId(Integer hrId) {
        return menuMapper.getAllMenuByHrId(hrId);
    }

    @Cacheable(key = "'allMenuWithRoles'")
    public List<Menu> getAllMenuWithRoles() {
        return menuMapper.getAllMenuWithRoles();
    }

    @Cacheable(key = "'allBasicMenu'")
    public List<BasicMenu> getAllBasicMenu() {
        return menuMapper.getAllBasicMenu();
    }

    public List<Integer> getAllMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getAllMenuIdsByRoleId(roleId);
    }

    @CacheEvict(key = "'allMenuWithRoles'")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addRoleMenu(List<Integer> menuIds, Integer roleId) {
        return menuMapper.addRoleMenu(menuIds, roleId);
    }

    @CacheEvict(key = "'allMenuWithRoles'")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteAllMenuByRoleId(Integer roleId) {
        return menuMapper.deleteAllMenuByRoleId(roleId);
    }

    @CacheEvict(key = "'allMenuWithRoles'")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteBatchAllMenuByRoleIds(List<Integer> roleIds) {
        return menuMapper.deleteBatchAllMenuByRoleIds(roleIds);
    }
}
