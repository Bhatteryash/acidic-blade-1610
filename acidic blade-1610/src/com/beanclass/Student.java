package com.beanclass;

public class Student {

	private int sid;
	private int cid;
	private String username;
	private String pass;
	
	public Student(int sid, int cid, String username) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.username = username;
		
	}
	public Student(int cid, String username, String pass) {
		super();
		this.cid = cid;
		
		this.username = username;
		this.pass = pass;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
