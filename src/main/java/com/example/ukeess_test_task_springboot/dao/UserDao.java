package com.example.ukeess_test_task_springboot.dao;

import com.example.ukeess_test_task_springboot.dtoModel.DAOUser;

public interface UserDao {
    DAOUser findByUsername(String username);
    int registerUser(DAOUser daoUser);
}
