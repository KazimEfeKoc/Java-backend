package com.kazimefe.controller;

import com.kazimefe.model.Employee;
import com.kazimefe.model.EmployeeRequest;
import com.kazimefe.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/employee")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getAllEmployeeList(){
        return employeeService.getAllEmployeeList();
    }

    @GetMapping("/list/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") int id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/with-params")
    public List<Employee> getEmployeeWithParams(@RequestParam(name = "firstName", required = false) String firstName,
                                                @RequestParam(name = "lastName", required = false) String lastName){
        return employeeService.getEmployeeWithParams(firstName,lastName);
    }

    @PostMapping("/save-employee")
    public Employee saveEmployee(@RequestBody Employee newEmployee){
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/delete-employee/{id}")
    public boolean deleteEmployee(@PathVariable(name = "id") int id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/put-employee/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") int id, @RequestBody EmployeeRequest request){
        return employeeService.updateEmployee(id, request);
    }
}
