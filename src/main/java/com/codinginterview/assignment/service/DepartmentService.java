package com.codinginterview.assignment.service;

import com.codinginterview.assignment.dto.DepartmentDTO;
import com.codinginterview.assignment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentDTO::fromEntity).collect(Collectors.toList());
    }

}
