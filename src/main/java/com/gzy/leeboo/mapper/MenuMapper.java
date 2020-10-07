package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.BasicMenu;
import com.gzy.leeboo.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> getAllMenuByHrId(Integer hrId);

    List<Menu> getAllMenuWithRoles();

    List<BasicMenu> getAllBasicMenu();

    List<Integer> getAllMenuIdsByRoleId(Integer roleId);

    Boolean addRoleMenu(List<Integer> menuIds, Integer roleId);

    Boolean deleteAllMenuByRoleId(Integer roleId);

    Boolean deleteBatchAllMenuByRoleIds(List<Integer> roleIds);
}