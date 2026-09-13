package com.kazimefe.repository;

import com.kazimefe.model.Employee;
import com.kazimefe.model.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private List<Employee> employeeList;

    public List<Employee> getAllEmployeeList() {
        return employeeList;
    }

    public Employee getEmployeeById(int id){
        Employee employee = null;
        for(Employee employee2: employeeList){
            if (employee2.getId() == id){
                employee = employee2;
                break;
            }
        }
        return employee;
    }

    public List<Employee> getEmployeeWithParams(String firstName, String lastName){
        List<Employee> newEmployeeList = new ArrayList<>();

        if (firstName == null && lastName == null){
            return employeeList;

        } else if (firstName!= null && lastName == null) {
            for (Employee employee: employeeList){
                if (employee.getFirstName().equals(firstName)){
                    newEmployeeList.add(employee);
                }
            }
        } else if (firstName == null) {
            for (Employee employee: employeeList){
                if (employee.getLastName().equals(lastName)){
                    newEmployeeList.add(employee);
                }
            }
        } else {
            for (Employee employee: employeeList){
                if (employee.getFirstName().equals(firstName)
                        && employee.getLastName().equals(lastName)){
                    newEmployeeList.add(employee);
                }
            }
        }
        return newEmployeeList;
    }

    public Employee addEmployee(Employee newEmployee){
        employeeList.add(newEmployee);
        return newEmployee;
    }

    public boolean deleteEmployee(int id){
        Employee employee = null;
        for (Employee employee1 : employeeList){
            if (employee1.getId() == id){
                employee = employee1;
                break;
            }
        }
        if (employee!= null){
            return employeeList.remove(employee);
        }
        return false;
    }

    public Employee updateEmployee(int id, EmployeeRequest request){
        Employee employee1 = null;
        for (Employee employee2 : employeeList){
            if (employee2.getId() == id){
                employee2.setFirstName(request.getFirstName());
                employee2.setLastName(request.getLastName());
                employee1 = employee2;
                break;
            }
        }
        return employee1;
    }
}
