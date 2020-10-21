package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Department;
import com.gzy.leeboo.mapper.DepartmentMapper;
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

@CacheConfig(cacheNames = "department")
@Service
public class DepartmentService {
    private DepartmentMapper departmentMapper;

    @Autowired
    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Cacheable(key = "'allDepartments'")
    public List<Department> getAllDepartments() {
        /*
         * 这里是使用递归来查找所有的部门，并且递归是通过MyBatis实现的
         * -1参数表示递归入口，数据库中将最顶层的部门id设置为-1
         */
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    @Cacheable(key = "'allBasicDepartments'")
    public List<Department> getAllBasicDepartments() {
        return departmentMapper.getAllBasicDepartments();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(key = "'allDepartments'"),
                    @CacheEvict(key = "'allBasicDepartments'")
            }
    )
    public Boolean addDepartment(Department department) {
        // 添加部门的基础信息（名称和父id）
        if (!departmentMapper.addDepartment(department)) {
            return false;
        }

        // 获取其父部门的路径
        String parentPath = departmentMapper.getPathById(department.getParentId());
        // 使用父部门路径和自己的id拼装自己的路径
        department.setPath(parentPath + "." + department.getId());

        // 修改部门路径
        if (!departmentMapper.updateDepartmentPath(department) || !departmentMapper.updateDepartmentIsParent(department.getParentId(), true)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(key = "'allDepartments'"),
                    @CacheEvict(key = "'allBasicDepartments'")
            }
    )
    public Boolean deleteDepartmentById(Integer id, Integer parentId) {
        // 删除该部门
        if (!departmentMapper.deleteDepartmentById(id)) {
            return false;
        }

        // 如果删除之后父部门已经无子部门了，就将父部门的isParent属性设置为false
        if (departmentMapper.getSubDeparmentCountsById(parentId) == 0) {
            if (!departmentMapper.updateDepartmentIsParent(parentId, false )) {
                return false;
            }
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Caching(
            evict = {
                    @CacheEvict(key = "'allDepartments'"),
                    @CacheEvict(key = "'allBasicDepartments'")
            }
    )
    public Boolean deleteBatchDepartmentByIds(List<Integer> ids, Integer parentId) {
        // 删除该部门
        if (!departmentMapper.deleteBatchDepartmentByIds(ids)) {
            return false;
        }

        // 如果删除之后父部门已经无子部门了，就将父部门的isParent属性设置为false
        if (departmentMapper.getSubDeparmentCountsById(parentId) == 0) {
            if (!departmentMapper.updateDepartmentIsParent(parentId, false)) {
                return false;
            }
        }
        return true;
    }
}
