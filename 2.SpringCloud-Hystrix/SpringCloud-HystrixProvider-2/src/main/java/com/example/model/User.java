package com.example.model;

public class User {
	public User()
	{
	}
	public User(int id, String name)
	{
		setUserId(id);
		setUserName(name);
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private int userId;
	private String userName;
}
