package com.codinginterview.assignment.dto;

import com.codinginterview.assignment.modal.Employee;

public class EmployeeDTO {
    private String id;
    private String name;
    private String email;
    private String position;
    private double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(String id, String name, String email, String position, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public static EmployeeDTO fromEntity(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getEmail(), employee.getPosition(), employee.getSalary());
    }
}

