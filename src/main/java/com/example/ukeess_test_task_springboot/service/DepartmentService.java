package com.example.ukeess_test_task_springboot.service;

import com.example.ukeess_test_task_springboot.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentByID(int id);
}
