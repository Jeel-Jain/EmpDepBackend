package com.codinginterview.assignment.dto;

import com.codinginterview.assignment.modal.Department;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DepartmentDTO {
    private String id;
    private String name;
    private String location;
    private List<EmployeeDTO> employees;

    public DepartmentDTO() {}

    public DepartmentDTO(String id, String name, String location, List<EmployeeDTO> employees) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static DepartmentDTO fromEntity(Department department) {
        List<EmployeeDTO> employeeDTOs = department.getEmployees().stream().map(EmployeeDTO::fromEntity).collect(Collectors.toList());
        return new DepartmentDTO(department.getId(), department.getName(), department.getLocation(), employeeDTOs);
    }
}
