package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;

import java.util.List;

public interface EmployeeDao {


    Employee getEmpById(int id);
    List<Employee> getAllEmps(int start, int size);
    int createNewEmployee(EmployeeDTO employeeDTO);
    int updateEmployee(EmployeeDTO employeeDTO, int id);
    int deleteEmployee(int id);
    List<Employee> findEmployeeByNameStart(String name);

}
