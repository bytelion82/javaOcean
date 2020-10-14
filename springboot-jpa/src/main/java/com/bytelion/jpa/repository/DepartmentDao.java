package com.bytelion.jpa.repository;

import com.bytelion.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.jpa.repository
 * @description
 * @date: Created in 2020/10/8 21:27
 * @copyright Copyright (c) 2020/10/8
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department,Long> {
    /**
     * 根据层级查询部门
     *
     * @param level 层级
     * @return 部门列表
     */
    List<Department> findDepartmentsByLevels(Integer level);
}
