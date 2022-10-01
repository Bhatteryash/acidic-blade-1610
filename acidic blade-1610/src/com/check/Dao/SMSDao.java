package com.check.Dao;

import java.util.List;

import com.beanclass.Batch;
import com.beanclass.Course;
import com.beanclass.Student;
import com.exception.Incorrectvalues;

public interface SMSDao {

	public boolean authenticateAdmin(String user,String pass) throws Incorrectvalues;
	
	public String AddCourse(int id,String name,int fee);
	
	public String UpdateFee(int cid,int newfee);
	
	public String DeleteCourse(int cid);
	
	public Course searchcourse(int cid);
	
	public String CreateBatch(Batch batch);
	
	public String AllocateBatch(int sid);
	
	public String UpdateSeats(int bid,int newseats);
	
	public List<Student> ViewStudent(int bid);
	
	
//	Student methods
	public String AddStudent(Student stu);
	
	public String updateStudent(int sid);
}
