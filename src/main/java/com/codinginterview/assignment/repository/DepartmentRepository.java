package com.codinginterview.assignment.repository;

import com.codinginterview.assignment.modal.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
