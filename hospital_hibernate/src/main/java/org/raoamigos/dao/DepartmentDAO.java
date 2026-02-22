package org.raoamigos.dao;


import org.raoamigos.entity.Department;

import java.util.List;

public interface DepartmentDAO {

    void save(Department department);

    Department findById(Long id);

    List<Department> getAllDepartments();

    void deleteById(Long id);
}
