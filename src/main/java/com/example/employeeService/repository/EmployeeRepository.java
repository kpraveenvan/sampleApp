package com.example.employeeService.repository;

import com.example.employeeService.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employees", collectionResourceRel="employees" )
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
