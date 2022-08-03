package com.example.ukeess_test_task_springboot.controller;

import com.example.ukeess_test_task_springboot.dtoModel.DtoResponsePagination;
import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Employee;
import com.example.ukeess_test_task_springboot.handlers.ConnectionFailedException;
import com.example.ukeess_test_task_springboot.handlers.InvalidDataInputException;
import com.example.ukeess_test_task_springboot.handlers.NoSuchEntityException;
import com.example.ukeess_test_task_springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    private boolean isValidDto(EmployeeDTO employeeDTO) {

        return
                employeeDTO.getDepartmentId() > 0 &&
                employeeDTO.getName() != null &&
                employeeDTO.getName().length() > 1;
    }

    private int[] parsePagination(Optional<String> index, Optional<String> size){

        int[] indexAndSize = new int[2];

        int beginIndex = 1;
        int pageSize = 1;
        try{
            beginIndex = Integer.parseInt(index.get());
            pageSize = Integer.parseInt(size.get());

            indexAndSize[0] = beginIndex;
            indexAndSize[1] = pageSize;
        } catch (NumberFormatException e){
            throw new InvalidDataInputException("Index and size of pages should be integers!");
        }
        if (beginIndex <= 0 || pageSize <= 0) {
            throw new InvalidDataInputException("Index and page size should be more then 0!");
        }

        return indexAndSize;
    }


    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        Employee employee = employeeService.getEmpById(id);
        if (employee == null)
            throw new NoSuchEntityException("No employee with id: " + id);

        return employee;
    }

    @GetMapping(value = {"/index={index}/size={size}", ""})
    public DtoResponsePagination getEmployeesInPages(@PathVariable Optional<String> index,
                                              @PathVariable Optional<String> size) {

        DtoResponsePagination response = null;

        if (index.isPresent() && size.isPresent()) {
            int[] indexAndSize = parsePagination(index, size);

            response = employeeService.getEmployees(indexAndSize[0], indexAndSize[1]);
        } else {
            response = employeeService.getEmployees(1, 5);
        }

        return response;
    }

    @GetMapping(value = {"/search/{name}/index={index}/size={size}", "/search/{name}"})
    public DtoResponsePagination searchEmployeesWithNameBegin(@PathVariable String name,
                                                       @PathVariable Optional<String> index,
                                                       @PathVariable Optional<String> size) {

        DtoResponsePagination response = null;
        if(name.isEmpty())
            throw new InvalidDataInputException("Put name which you want to find!");

        if (index.isPresent() && size.isPresent()) {

            int[] indexAndSize = parsePagination(index, size);

            response = employeeService.getEmployees(name, indexAndSize[0], indexAndSize[1]);
        } else {
            response = employeeService.getEmployees(name, 1, 5);
        }

        return response;
    }


    //
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {

        if (!isValidDto(employeeDTO))
            throw new InvalidDataInputException("You put invalid data!");

        int result = employeeService.createNewEmployee(employeeDTO);
        if (result == 0)
            throw new ConnectionFailedException("Employee was not created");


        return employeeDTO;
    }

    //
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO updateEmployee(@PathVariable int id,
                                      @RequestBody EmployeeDTO employeeDTO) {

        if (!isValidDto(employeeDTO))
            throw new InvalidDataInputException("You put invalid data!");

        int result = employeeService.updateEmployee(employeeDTO, id);
        if (result == 0)
            throw new ConnectionFailedException("Employee was not updated");

        return employeeDTO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteEmployee(@PathVariable int id) {

        if (employeeService.deleteEmployee(id) == 0)
            throw new NoSuchEntityException("No employee with id: " + id);
    }

}
