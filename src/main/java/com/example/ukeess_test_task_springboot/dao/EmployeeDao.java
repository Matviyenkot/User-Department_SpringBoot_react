package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    int getTotalSize(String sql);
    Employee getEmpById(int id);
    List<Employee> getEmployees(String sql);
    int createNewEmployee(EmployeeDTO employeeDTO);
    int updateEmployee(EmployeeDTO employeeDTO, int id);
    int deleteEmployee(int id);

}
