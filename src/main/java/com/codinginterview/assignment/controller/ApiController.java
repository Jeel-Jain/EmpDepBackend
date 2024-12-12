package com.codinginterview.assignment.controller;

import com.codinginterview.assignment.dto.DepartmentDTO;
import com.codinginterview.assignment.dto.EmployeeDTO;
import com.codinginterview.assignment.modal.Employee;
import com.codinginterview.assignment.service.DepartmentService;
import com.codinginterview.assignment.service.EmployeeService;
import com.codinginterview.assignment.service.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JasperReportService reportService;

    // Get all employees
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // Get all departments
    @GetMapping("/departments")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get employees in a department
    @GetMapping("/departments/{departmentId}/employees")
    public List<EmployeeDTO> getEmployeesInDepartment(@PathVariable String departmentId) {
        return employeeService.getEmployeesInDepartment(departmentId);
    }

    // Add a new employee to a department
    @PostMapping("/departments/{departmentId}/employees")
    public EmployeeDTO addEmployeeToDepartment(@PathVariable String departmentId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployeeToDepartment(departmentId, employeeDTO);
    }

    // Delete an employee from a department
    @DeleteMapping("/departments/{departmentId}/employees/{employeeId}")
    public void deleteEmployeeFromDepartment(@PathVariable String departmentId, @PathVariable String employeeId) {
        employeeService.deleteEmployeeFromDepartment(departmentId, employeeId);
    }


    @GetMapping("/department/{id}")
    public ResponseEntity<byte[]> getDepartmentEmployeeReport(@PathVariable String id) {
        try {
            JasperPrint jasperPrint = reportService.generateDepartmentEmployeeReport(id);
            byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("department_employee_report.pdf").build());

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (JRException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
