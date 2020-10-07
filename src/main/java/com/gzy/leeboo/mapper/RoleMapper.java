package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.UpdateHrRole;
import com.gzy.leeboo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> getAllRoles();

    Boolean addRole(Role role);

    Boolean addHrRole(UpdateHrRole updateHrRole);

    Boolean updateRole(Role role);

    Boolean deleteAllHrRolesByHrId(Integer hrId);

    Boolean deleteRoleById(Integer id);

    Boolean deleteBatchRoleByIds(List<Integer> ids);
}
