package com.beanclass;

public class Admin {

	private String name;
	private String username;
	private String pass;
	public Admin(String name, String username, String pass) {
		super();
		this.name = name;
		this.username = username;
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
