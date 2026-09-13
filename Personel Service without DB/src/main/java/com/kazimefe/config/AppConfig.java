package com.kazimefe.config;

import com.kazimefe.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Employee> getAllEmployeeList(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"Kazim","Koc"));
        employeeList.add(new Employee(2,"Efe","Koc"));
        employeeList.add(new Employee(3,"Ahmet","Birisi"));

        return employeeList;
    }
}
