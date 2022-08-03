package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private DataSource dataSource;

    public DAOUser findByUsername(String username){

            DAOUser user = null;
            try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()){

                String sql = "select * from user where username = '" + username + "'";

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()){
                    user = new DAOUser(rs.getString("username"), rs.getString("password"));
                }
            }catch (
                    SQLException e) {
                e.printStackTrace();
            }

        return user;
    }

    public int registerUser(DAOUser daoUser){

        int rs = 0;
        try (Connection connection = dataSource.getConnection(); Statement st = connection.createStatement()){

            String sql = "insert into user set username = '" + daoUser.getUsername() + "', " +
                    "password = '" + daoUser.getPassword() + "' ";

            rs = st.executeUpdate(sql);

        }catch (
                SQLException e) {
            e.printStackTrace();
        }

        if(rs != 0) {
            return 1;
        }
        else
            return 0;
    }


}
