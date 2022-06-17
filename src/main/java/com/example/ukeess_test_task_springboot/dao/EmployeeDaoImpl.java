package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.EmployeeDTO;
import com.example.ukeess_test_task_springboot.entity.Department;
import com.example.ukeess_test_task_springboot.entity.Employee;
import com.example.ukeess_test_task_springboot.handlers.ConnectionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

//    @Autowired
//    private Connector connector;

    @Autowired
    private DataSource dataSource;


    public Employee getEmpById(int id) {

//        Connection connection = null;
//        Statement st = null;
        Employee employee = null;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = dataSource.getConnection();
//            st = connection.createStatement();

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


    // зробити пагінацію по порядку (ready)
    public List<Employee> getAllEmps(int index, int size) {

//        Connection connection = null;
//        Statement st = null;
        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = dataSource.getConnection();
//            st = connection.createStatement();

            int start = ((index - 1) * size);

            String sql = "select employees.id, employees.empName, employees.empActive, departments.dpID, departments.dpName " +
                    "from departments " +
                    "inner join employees on employees.empDPid = departments.dpID " +
                    "ORDER BY employees.id " +
                    "LIMIT " + start + ", " + size;

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
//        finally {
//            try {
//                st.close();
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return employeeList;
    }


    //
//
//    //чекнути шо вертає апдейт
    public int createNewEmployee(EmployeeDTO employeeDTO) {

//        Connection connection = null;
//        Statement st = null;

        int result = 0;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = getConnection();
//            st = connection.createStatement();

            String sql = "insert into Employees set empName = '" + employeeDTO.getName() + "', empActive = "
                    + employeeDTO.isActive() + ", empDPid = " + employeeDTO.getDepartmentId();

            result = st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //
//    //check return statement
    public int updateEmployee(EmployeeDTO employeeDTO, int id) {
//        Connection connection = null;
//        Statement st = null;

        int result = 0;

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = getConnection();
//            st = connection.createStatement();

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
//        Connection connection = null;
//        Statement st = null;
        int result = 0;
        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = getConnection();
//            st = connection.createStatement();

            String sql = "delete from employees where id = " + id;
            result = st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    //
//
//
//    //check limit in sql
    public List<Employee> findEmployeeByNameStart(String name) {
//        Connection connection = null;
//        Statement st = null;

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()) {
//            connection = getConnection();
//            st = connection.createStatement();
            String sql = "select employees.id, employees.empName, employees.empActive, departments.dpID, departments.dpName " +
                    "from departments " +
                    "inner join employees on employees.empDPid = departments.dpID " +
                    "where empName like '" + name + "%' " +
                    "limit " + 0 + ", " + 5;

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


}
