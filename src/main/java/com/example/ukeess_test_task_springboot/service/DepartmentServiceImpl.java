package com.example.ukeess_test_task_springboot.service;

import com.example.ukeess_test_task_springboot.dao.DepartmentDao;
import com.example.ukeess_test_task_springboot.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public Department getDepartmentByID(int id) {
        return departmentDao.getDepartmentByID(id);
    }
}
