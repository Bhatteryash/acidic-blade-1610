package com.beanclass;

public class Batch {

	private int BatchId;
	private int CourseId;
	private int Seats;
	private String slot;
	public Batch(int batchId, int courseId, int seats, String slot) {
		super();
		BatchId = batchId;
		CourseId = courseId;
		Seats = seats;
		this.slot = slot;
	}
	public int getBatchId() {
		return BatchId;
	}
	public void setBatchId(int batchId) {
		BatchId = batchId;
	}
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public int getSeats() {
		return Seats;
	}
	public void setSeats(int seats) {
		Seats = seats;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	
}
