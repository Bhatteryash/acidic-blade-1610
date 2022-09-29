package com.check.Dao;

import com.exception.Incorrectvalues;

public interface SMSDao {

	public boolean authenticateAdmin(String user,String pass) throws Incorrectvalues;
	
	public String AddCourse(int id,String name,int fee);
	
	public String UpdateFee(int cid,int newfee);
	
	
}
