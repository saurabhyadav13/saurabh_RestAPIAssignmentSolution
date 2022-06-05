package com.greatlearning.employeemgmt.service;

import com.greatlearning.employeemgmt.entity.Employee;
import com.greatlearning.employeemgmt.entity.Role;
import com.greatlearning.employeemgmt.entity.User;
import com.greatlearning.employeemgmt.repository.EmployeeRepository;
import com.greatlearning.employeemgmt.repository.RoleRepository;
import com.greatlearning.employeemgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRepository empRepository;
    // CREATE
    public Employee createEmployee(Employee emp) {
        return empRepository.save(emp);
    }

    // READ
    public List<Employee> getEmployees() {
        return empRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(Integer id) {
        System.out.println(id);
        System.out.println(empRepository.findById(id));
        return empRepository.findById(id);
    }

    // DELETE
    public void deleteEmployee(Integer empId) {
        empRepository.deleteById(empId);
    }
    // UPDATE
    public Employee updateEmployee(Integer empId, Employee employeeDetails) {
        Employee emp = empRepository.findById(empId).get();
        emp.setFirstName(employeeDetails.getFirstName());
        emp.setLastName(employeeDetails.getLastName());
        emp.setEmailId(employeeDetails.getEmailId());

        return empRepository.save(emp);
    }


    public List<Employee> searchEmployee(String firstName) {
        return empRepository.findByFirstNameContainsAllIgnoreCase(firstName);
    }

    public List<Employee> sortByFirstName(String order) {
        if(order.equals("ASC")){
        return empRepository.findAllByOrderByFirstNameAsc();
        }else {
            return empRepository.findAllByOrderByFirstNameDesc();
        }
    }
    public User createUser(User user) {
        String rawPassword;
        rawPassword = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        user.setPassword(rawPassword);
        return user;
    }
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
