package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Department;
import com.example.ukeess_test_task_springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private DataSource dataSource;


    @Override
    public int getTotalSize(String sql1) {

        int count = 0;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()){

            ResultSet rs = st.executeQuery(sql1);

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public Employee getEmpById(int id) {

        Employee employee = null;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {

            String sql = "select employees.id, employees.empName, employees.empActive, departments.dpID, departments.dpName " +
                    "from departments " +
                    "inner join employees on employees.empDPid = departments.dpID " +
                    "where id = " + id;

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("empName"),
                        rs.getBoolean("empActive")
                        , new Department(rs.getInt("dpID"), rs.getString("dpName")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;

    }


    public List<Employee> getEmployees(String sql) {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("empName"),
                        rs.getBoolean("empActive")
                        , new Department(rs.getInt("dpID"), rs.getString("dpName")));

                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public int createNewEmployee(EmployeeDTO employeeDTO) {


        int result = 0;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {

            String sql = "insert into Employees set empName = '" + employeeDTO.getName() + "', empActive = "
                    + employeeDTO.isActive() + ", empDPid = " + employeeDTO.getDepartmentId();

            result = st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public int updateEmployee(EmployeeDTO employeeDTO, int id) {

        int result = 0;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {

            String sql = "update employees set empName = '" + employeeDTO.getName() +
                    "', empActive = " + employeeDTO.isActive() + ", empDPid = " + employeeDTO.getDepartmentId() +
                    " where id = " + id;

            result = st.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    public int deleteEmployee(int id) {
        int result = 0;
        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {

            String sql = "delete from employees where id = " + id;
            result = st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
}
