package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.model.Department;
import com.bugtracker.bugtracker.model.Employee;

import java.util.List;


public interface DepartmentService{
    List<Department> getAllDepartment();
    Department saveDepartment(Department department);

    Department updateDepartment(Department department);
    Department getDepartmentById(long id);



    void deleteDepartmentById(long id);
}
