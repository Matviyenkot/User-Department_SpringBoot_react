package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartments();
    Department getDepartmentByID(int id);
}
