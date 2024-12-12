package com.codinginterview.assignment.service;

import com.codinginterview.assignment.dto.EmployeeDTO;
import com.codinginterview.assignment.modal.Department;
import com.codinginterview.assignment.modal.Employee;
import com.codinginterview.assignment.repository.DepartmentRepository;
import com.codinginterview.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<EmployeeDTO> getAllEmployees() {
        System.out.println("In repo");
        return employeeRepository.findAll().stream().map(EmployeeDTO::fromEntity).collect(Collectors.toList());
    }
    public EmployeeDTO getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeDTO.fromEntity(employee);
    }

    public List<EmployeeDTO> getEmployeesInDepartment(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        return department.getEmployees().stream().map(EmployeeDTO::fromEntity).collect(Collectors.toList());
    }

    public EmployeeDTO addEmployeeToDepartment(String departmentId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        Employee employee = new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getPosition(), employeeDTO.getSalary());
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return EmployeeDTO.fromEntity(employee);
    }

    public void deleteEmployeeFromDepartment(String departmentId, String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        if (!employee.getDepartment().getId().equals(departmentId)) {
            throw new RuntimeException("Employee does not belong to the specified department");
        }
        employeeRepository.delete(employee);
    }
}
