package com.example.ukeess_test_task_springboot.dtoModel;


public class DAOUser {

	private int id;
	private String username;
	private String password;

	public DAOUser() {
	}

	public DAOUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}