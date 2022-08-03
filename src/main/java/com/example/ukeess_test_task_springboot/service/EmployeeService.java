package com.example.ukeess_test_task_springboot.service;

import com.example.ukeess_test_task_springboot.dtoModel.DtoResponsePagination;
import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;


public interface EmployeeService {

    Employee getEmpById(int id);
    int createNewEmployee(EmployeeDTO employeeDTO);
    int updateEmployee(EmployeeDTO employeeDTO, int id);
    int deleteEmployee(int id);
    DtoResponsePagination getEmployees(String name, int index, int size);

    DtoResponsePagination getEmployees(int index, int size);

}
