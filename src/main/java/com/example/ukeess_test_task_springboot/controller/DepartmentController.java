package com.example.ukeess_test_task_springboot.controller;

import com.example.ukeess_test_task_springboot.dao.DepartmentDaoImpl;
import com.example.ukeess_test_task_springboot.entity.Department;
import com.example.ukeess_test_task_springboot.handlers.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentDaoImpl departmentDao;

    @GetMapping("/getDepartments")
    public List<Department> getAllDepartments(){

        return departmentDao.getAlldepartments();
    }

    @GetMapping("/getDepartments/{id}")
    public Department getDepartmentById(@PathVariable int id){
        Department department = departmentDao.getDepartmentByID(id);

        if(department == null)
            throw new NoSuchEntityException("No Department with id" + id);
        return department;
    }
}
