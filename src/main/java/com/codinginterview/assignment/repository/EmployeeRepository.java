package com.codinginterview.assignment.repository;

import com.codinginterview.assignment.modal.Department;
import com.codinginterview.assignment.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    List<Employee> findByDepartment(Department department);
}
