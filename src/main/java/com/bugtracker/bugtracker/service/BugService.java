package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.model.Department;
import com.bugtracker.bugtracker.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface EmployeeService extends UserDetailsService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

    public List<Employee> findByKeyWord(String keyword);




}
