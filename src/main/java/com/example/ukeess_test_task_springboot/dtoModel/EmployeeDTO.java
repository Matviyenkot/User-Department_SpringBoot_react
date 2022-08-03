package com.example.ukeess_test_task_springboot.dtoModel;

public class EmployeeDTO {

    private String name;
    private boolean isActive;
    private int departmentId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, boolean isActive, int departmentId) {
        this.name = name;
        this.isActive = isActive;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
