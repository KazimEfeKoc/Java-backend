package com.kazimefe.services;

import com.kazimefe.model.Employee;
import com.kazimefe.model.EmployeeRequest;
import com.kazimefe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeList(){
        return employeeRepository.getAllEmployeeList();
    }

    public Employee getEmployeeById(int id){
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getEmployeeWithParams(String firstName, String lastName){
        return employeeRepository.getEmployeeWithParams(firstName,lastName);
    }

    public Employee saveEmployee(Employee newEmployee){
        return employeeRepository.addEmployee(newEmployee);
    }

    public boolean deleteEmployee(int id){
        return employeeRepository.deleteEmployee(id);
    }

    public Employee updateEmployee(int id, EmployeeRequest request){
        return employeeRepository.updateEmployee(id, request);
    }
}
