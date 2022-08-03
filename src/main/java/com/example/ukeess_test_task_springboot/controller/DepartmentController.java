package com.example.ukeess_test_task_springboot.controller;

import com.example.ukeess_test_task_springboot.dao.DepartmentDao;
import com.example.ukeess_test_task_springboot.entity.Department;
import com.example.ukeess_test_task_springboot.handlers.NoSuchEntityException;
import com.example.ukeess_test_task_springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentDao;

    @GetMapping("")
    public List<Department> getAllDepartments(){

        return departmentDao.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id){

        if(id <= 0)
            throw new NoSuchEntityException("No Department with id" + id);

        Department department = departmentDao.getDepartmentByID(id);

        if(department == null)
            throw new NoSuchEntityException("No Department with id" + id);
        return department;
    }
}
