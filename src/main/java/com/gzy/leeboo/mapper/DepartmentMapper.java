package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    List<Department> getAllDepartmentsByParentId(Integer parentId);

    List<Department> getAllBasicDepartments();

    Boolean addDepartment(Department department);

    String getPathById(Integer id);

    Boolean updateDepartmentPath(Department department);

    Boolean updateDepartmentIsParent(@Param("id")Integer id, @Param("isParent") Boolean isParent);

    Integer getSubDeparmentCountsById(Integer id);

    Boolean deleteDepartmentById(Integer id);

    Boolean deleteBatchDepartmentByIds(List<Integer> departmentIds);
}
