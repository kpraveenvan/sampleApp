package com.example.employeeService;

import com.example.employeeService.model.Employee;
import com.example.employeeService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Profile("default")
public class LoadData {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void loadEmployees() {

        Employee employee1 = new Employee(1, "Praveen", "Vanga", 1);
        Employee employee2 = new Employee(2, "Paul", "Rodd", 1);
        Employee employee3 = new Employee(3, "Bruce", "Banner", 1);
        Employee employee4 = new Employee(4, "Timothy", "Holt", 1);
        Employee employee5 = new Employee(5, "Chris", "Evins", 1);
        Employee employee6 = new Employee(6, "Donald", "Trump", 1);

        employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
    }
}
