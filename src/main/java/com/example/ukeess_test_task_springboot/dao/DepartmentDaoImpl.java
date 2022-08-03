package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

    @Autowired
    private DataSource dataSource;

    public List<Department> getAllDepartments(){

        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()){

            String sql = "select * from departments";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                departmentList.add(new Department(rs.getInt("dpID"), rs.getString("dpName")));
            }
        }catch (
                SQLException e) {
            e.printStackTrace();
        }

        return departmentList;
    }

    public Department getDepartmentByID(int id){

        Department department = null;
        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()){

            String sql = "select * from departments where dpId = " + id;

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                department = new Department(rs.getInt("dpID"), rs.getString("dpName"));
            }
        }catch (
                SQLException e) {
            e.printStackTrace();
        }

        return department;
    }


}
