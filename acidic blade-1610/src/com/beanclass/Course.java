package com.beanclass;

public class Course {

	private int Cid;
	private String name;
	private int fee;
	private int seats;
	
	public Course(int cid,String name) {
		Cid = cid;
		this.name = name;
	}
	
	public Course(int cid, String name, int fee, int seats) {
		super();
		Cid = cid;
		this.name = name;
		this.fee = fee;
		this.seats = seats;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public Course(int cid, String name, int fee) {
		
		Cid = cid;
		this.name = name;
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "Course [Cid=" + Cid + ", name=" + name + ", fee=" + fee + "]";
	}
	public Course() {
		
	}
	
}
