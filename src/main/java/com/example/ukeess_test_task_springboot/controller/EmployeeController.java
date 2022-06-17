package com.example.ukeess_test_task_springboot.controller;

import com.example.ukeess_test_task_springboot.dao.DepartmentDaoImpl;
import com.example.ukeess_test_task_springboot.dao.EmployeeDao;
import com.example.ukeess_test_task_springboot.dao.EmployeeDaoImpl;
import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;
import com.example.ukeess_test_task_springboot.handlers.ConnectionFailedException;
import com.example.ukeess_test_task_springboot.handlers.InvalidDataInputException;
import com.example.ukeess_test_task_springboot.handlers.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDaoImpl departmentDao;

    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }

    //depId > 0
    //return true if valid
    private boolean isValidDto(EmployeeDTO employeeDTO) {

        return employeeDTO.getDepartmentId() > 0 &&
                employeeDTO.getName() != null &&
                employeeDTO.getName().length() > 1;
    }

    //can be invalid id
    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        Employee employee = employeeDao.getEmpById(id);
        if (employee == null)
            throw new NoSuchEntityException("No employee with id: " + id);

        return employee;
    }

    //index should be from 1
    //can return empty list
    //can be input not an int
    @GetMapping(value = {"/employees/index={index}/size={size}", "/employees"})
    public List<Employee> getEmployeesInPages(@PathVariable Optional<String> index,
                                              @PathVariable Optional<String> size) {

        List<Employee> employeeList = null;
        if (index.isPresent() && size.isPresent()) {
            int beginIndex = 1;
            int pageSize = 1;
            try{
                beginIndex = Integer.parseInt(index.get());
                pageSize = Integer.parseInt(size.get());
            } catch (NumberFormatException e){
                throw new InvalidDataInputException("Index and size of pages should be integers!");
            }

            if (beginIndex <= 0) {
                throw new InvalidDataInputException("Index should be more then 0!");
            }

            employeeList = employeeDao.getAllEmps(beginIndex, pageSize);
        } else {
            employeeList = employeeDao.getAllEmps(1, 5);
        }

        return employeeList;
    }


    //
    @PostMapping(value = "/createEmp")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {

        if (!isValidDto(employeeDTO))
            throw new InvalidDataInputException("You put invalid data!");

        int result = employeeDao.createNewEmployee(employeeDTO);
        if (result == 0)
            throw new ConnectionFailedException("Employee was not created");


        return employeeDTO;
    }

    //
    @PutMapping(value = "/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable int id,
                                      @RequestBody EmployeeDTO employeeDTO) {

        if (!isValidDto(employeeDTO))
            throw new InvalidDataInputException("You put invalid data!");

        int result = employeeDao.updateEmployee(employeeDTO, id);
        if (result == 0)
            throw new ConnectionFailedException("Employee was not updated");

        return employeeDTO;
    }

    //can be not valid id
    @DeleteMapping(value = "/delete/{id}")
    public void deleteEmployee(@PathVariable int id) {

        if (employeeDao.deleteEmployee(id) == 0)
            throw new NoSuchEntityException("No employee with id: " + id);
    }

    //list can be empty
    @GetMapping(value = "/search/{name}")
    public List<Employee> searchEmployeesWithNameBegin(@PathVariable String name) {

        return employeeDao.findEmployeeByNameStart(name);
    }
}
