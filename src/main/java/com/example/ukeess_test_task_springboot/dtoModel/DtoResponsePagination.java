package com.example.ukeess_test_task_springboot.dtoModel;

import com.example.ukeess_test_task_springboot.entity.Employee;
import com.example.ukeess_test_task_springboot.pagination.PageInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DtoResponsePagination {

    @JsonProperty("employees")
    private List<Employee> employeeList;
    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    public DtoResponsePagination() {
    }

    public DtoResponsePagination(List<Employee> employeeList, PageInfo pageInfo) {
        this.employeeList = employeeList;
        this.pageInfo = pageInfo;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
