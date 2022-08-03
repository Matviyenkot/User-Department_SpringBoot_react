package com.example.ukeess_test_task_springboot.service;

import com.example.ukeess_test_task_springboot.dao.EmployeeDao;
import com.example.ukeess_test_task_springboot.dtoModel.DtoResponsePagination;
import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;
import com.example.ukeess_test_task_springboot.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Employee getEmpById(int id) {
        return employeeDao.getEmpById(id);
    }

    @Override
    public int createNewEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.createNewEmployee(employeeDTO);
    }

    @Override
    public int updateEmployee(EmployeeDTO employeeDTO, int id) {
        return employeeDao.updateEmployee(employeeDTO, id);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public DtoResponsePagination getEmployees(String name, int index, int size) {

        int start = ((index - 1) * size);
        String sql = "select employees.id, employees.empName, employees.empActive, departments.dpID, departments.dpName " +
                "from departments " +
                "inner join employees on employees.empDPid = departments.dpID " +
                "where empName like '" + name + "%' " +
                "ORDER BY employees.id " +
                "limit " + start + ", " + size;

        String countSql = "SELECT COUNT(id) " +
                "FROM employees " +
                "where empName like '" + name + "%' ";

        List<Employee> list = employeeDao.getEmployees(sql);
        int total = employeeDao.getTotalSize(countSql);

        DtoResponsePagination response = new DtoResponsePagination(list, new PageInfo(index, size, total));

        return response;
    }

    @Override
    public DtoResponsePagination getEmployees(int index, int size) {

        int start = ((index - 1) * size);

        String sql = "select employees.id, employees.empName, employees.empActive, departments.dpID, departments.dpName " +
                "from departments " +
                "inner join employees on employees.empDPid = departments.dpID " +
                "ORDER BY employees.id " +
                "LIMIT " + start + ", " + size;

        String countSql = "SELECT COUNT(id) " +
                "FROM employees";

        List<Employee> list = employeeDao.getEmployees(sql);
        int total = employeeDao.getTotalSize(countSql);

        DtoResponsePagination response = new DtoResponsePagination(list, new PageInfo(index, size, total));

        return response;
    }
}
