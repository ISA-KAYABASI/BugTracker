package com.bugtracker.bugtracker.service.Impl;

import com.bugtracker.bugtracker.model.Actor;
import com.bugtracker.bugtracker.model.Employee;
import com.bugtracker.bugtracker.model.Role;
import com.bugtracker.bugtracker.repository.ActorRepository;
import com.bugtracker.bugtracker.repository.DepartmentRepository;
import com.bugtracker.bugtracker.repository.EmployeeRepository;
import com.bugtracker.bugtracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public void saveEmployee(Employee employee){

        //First name first letter to uppercase rest is lowercase
        int firstnamelength = employee.getTitle().length();
        employee.setTitle(employee.getTitle().substring(0,1).toUpperCase()+(employee.getTitle().substring(1,firstnamelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int lastnameLength = employee.getLastName().length();
        employee.setLastName(employee.getLastName().substring(0,1).toUpperCase()+(employee.getLastName().substring(1,lastnameLength).toLowerCase()));


        // Decimal format made it halfway
        String salaryFormat = String.format("%15.2f",employee.getSalary());
        employee.setSalary(Double.valueOf(salaryFormat));

//        Department newDep = departmentRepository.save(employee.getDepartmentName())
        this.employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        //First name first letter to uppercase rest is lowercase
        int firstnamelength = employee.getTitle().length();
        employee.setTitle(employee.getTitle().substring(0,1).toUpperCase()+(employee.getTitle().substring(1,firstnamelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int lastnameLength = employee.getLastName().length();
        employee.setLastName(employee.getLastName().substring(0,1).toUpperCase()+(employee.getLastName().substring(1,lastnameLength).toLowerCase()));





        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {


        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee= null;
        if(optional.isPresent()){
            employee = optional.get();
        }else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByKeyWord(String keyword) {
        return employeeRepository.findByKeyWord(keyword);
    }





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Actor actor = actorRepository.findByFirstName(username);

        if( actor == null ){
            throw new UsernameNotFoundException("Invalid username or password.");
        }else{
                return new org.springframework.security.core.userdetails.User(actor.getFirstName(),actor.getPassword(),mapRolesToAuthorities(actor.getActorRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles ){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
