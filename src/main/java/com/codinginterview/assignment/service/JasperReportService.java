package com.codinginterview.assignment.service;

import com.codinginterview.assignment.modal.Department;
import com.codinginterview.assignment.modal.Employee;
import com.codinginterview.assignment.repository.DepartmentRepository;
import com.codinginterview.assignment.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {


        @Autowired
        private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

        public JasperPrint generateDepartmentEmployeeReport(String departmentId) throws JRException {
            // Load the compiled Jasper file
            InputStream jasperStream = getClass().getResourceAsStream("/reports/EmployeeByDepartment.jasper");

            // Get department and employees data from the database
            Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
            List<Employee> employees = employeeRepository.findByDepartment(department);
            System.out.println(employees);
            if (employees.isEmpty()) {
                throw new RuntimeException("No employees found for the department");
            }

            // Fill the report with data
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DEPARTMENT_NAME", department.getName());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);

            return jasperPrint;
        }
    }


