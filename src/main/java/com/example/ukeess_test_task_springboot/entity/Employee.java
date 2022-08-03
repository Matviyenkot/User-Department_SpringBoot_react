package com.example.ukeess_test_task_springboot.entity;

public class Employee {


    private int id;

    private String name;

    private boolean  isActive;

    private Department department;


    public Employee() {
    }

    public Employee(int id, String name, boolean isActive, Department department) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", department=" + department +
                '}';
    }
}
