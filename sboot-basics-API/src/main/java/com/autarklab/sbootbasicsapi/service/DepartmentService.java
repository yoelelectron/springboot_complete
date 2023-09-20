package com.autarklab.sbootbasicsapi.service;

import com.autarklab.sbootbasicsapi.ErrorHandler.DepartmentNotFoundException;
import com.autarklab.sbootbasicsapi.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> getDepartments();

    Department getDepartment(Long id) throws DepartmentNotFoundException;

    String deleteDepartment(Long id);

    Boolean isPresentDepartment(Long id);

    Department modifyDepartment(Long id, Department department) throws DepartmentNotFoundException;

    Department getByName(String name);
}
