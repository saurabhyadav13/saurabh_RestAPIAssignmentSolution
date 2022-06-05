package com.greatlearning.employeemgmt.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.greatlearning.employeemgmt.entity.Role;
import com.greatlearning.employeemgmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.employeemgmt.entity.Employee;
import com.greatlearning.employeemgmt.service.EmployeeService;

@RestController
//@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService empService;
    @RequestMapping(value="/employees", method=RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee emp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
        System.out.println(currentPrincipalName);
        emp.setId(0);
        return empService.createEmployee(emp);
    }
    @RequestMapping(value="/employees", method=RequestMethod.GET)
    public List<Employee> readEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
        System.out.println(currentPrincipalName);
        return empService.getEmployees();
    }

    @RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
    public Employee readEmployees(@PathVariable(value = "empId") Integer id, @RequestBody Employee empDetails) {
        return empService.updateEmployee(id, empDetails);
    }

    @RequestMapping(value="/employees/{empId}", method=RequestMethod.DELETE)
    public void deleteEmployees(@PathVariable(value = "empId") Integer id) {
        empService.deleteEmployee(id);
    }
    @GetMapping(value="/employees/{empId}")
    public Optional<Employee> readEmployee(@PathVariable(value = "empId") Integer id) {
        return empService.getEmployeeById(id);
    }

    @RequestMapping(value="/employees/search/{firstName}", method=RequestMethod.GET)
    public List<Employee> searchEmployees(@PathVariable(value = "firstName") String firstName) {
        return empService.searchEmployee(firstName);
    }

    @GetMapping("/employees/sort")
    public List<Employee> sortByFirstName(@RequestParam(name="order") String order){
        return empService.sortByFirstName(order);
    }

    @RequestMapping(value="/adduser", method= RequestMethod.POST)
    public User createUser(@RequestBody User usr) {
        return empService.createUser(usr);
    }
    @RequestMapping(value="/addrole", method= RequestMethod.POST)
    public Role createRole(@RequestBody Role role) {
        return empService.createRole(role);
    }

}
