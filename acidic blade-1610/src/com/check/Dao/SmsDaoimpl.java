package com.check.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beanclass.Batch;
import com.beanclass.Course;
import com.beanclass.Student;
import com.connection.Server;
import com.exception.Incorrectvalues;

public class SmsDaoimpl implements SMSDao {

	@Override
	public boolean authenticateAdmin(String user, String pass) throws Incorrectvalues {
		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where username=? and password=?");

			ps.setString(1, user);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			throw new Incorrectvalues("Technical error...");
		}
	}

	@Override
	public String AddCourse(int id, String name, int fee) {

		String message = "Not inserted....";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into course values(?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, fee);

			int x = ps.executeUpdate();
			if (x > 0)
				message = "Course added Sucessfully";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String UpdateFee(int cid, int newfee) {
		String message = "Not Changed";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(" update course set fee=? where cid=?");

			ps.setInt(1, newfee);
			ps.setInt(2, cid);

			int x = ps.executeUpdate();
			if (x > 0)
				message = "Fees Updated";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String DeleteCourse(int cid) {
		String message = "Not Deleted";
		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from course where cid=?");

			ps.setInt(1, cid);

			int x = ps.executeUpdate();
			if (x > 0)
				message = "Course Deleted";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public Course searchcourse(int cid) {
		Course c1 = new Course();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from course where cid=?");

			ps.setInt(1, cid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				c1.setCid(rs.getInt("cid"));
				c1.setName(rs.getString("cname"));
				c1.setFee(rs.getInt("fee"));
			}

		} catch (SQLException e) {

		}
		return c1;
	}

	@Override
	public String CreateBatch(Batch b) {
		String message = "Not inserted..";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into batch values(?,?,?,?)");

			ps.setInt(1, b.getBatchId());
			ps.setInt(2, b.getCourseId());
			ps.setInt(3, b.getSeats());
			ps.setString(4, b.getSlot());

			int x = ps.executeUpdate();
			if (x > 0)
				message = "Batch inserted";
		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String AllocateBatch(int sid) {
		String message ="No batch Found";
		
		
		try (Connection conn = Server.provideConnection()) {
			
			int bid=0;
			PreparedStatement ps1= conn.prepareStatement("select cid from student where sid="+sid+"");
			ResultSet rs= ps1.executeQuery();
			if(rs.next()) {
				int n=rs.getInt("cid");
				bid=getbid(n);
			}
			if(bid!=0) {
			PreparedStatement ps= conn.prepareStatement("update student set bid=? where sid=?");
			ps.setInt(1, bid);
			ps.setInt(2, sid);
			
			int x= ps.executeUpdate();
			if(x>0) message="Batch Allocated is "+bid;
			
		}
		}
		catch(SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;
	}

	@Override
	public String UpdateSeats(int bid, int newseats) {
		String message ="Seats Not updated";
		
		try(Connection conn=Server.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("update batch set seats=? where bid=?");
			
			ps.setInt(1, newseats);
			ps.setInt(2, bid);
			 if(ps.executeUpdate()>0) {
				 message="Seats Updated";
			 }
		}
		catch(SQLException e) {
			message=e.getMessage();
		}
		return message;
	}
	
	@Override
	public List<Student> ViewStudent(int bid) {
		List<Student> list=new ArrayList<>();
		
		try(Connection conn=Server.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select * from student where bid=?");
			ps.setInt(1, bid);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int sid=rs.getInt("sid");
				int cid=rs.getInt("cid");
				String username =rs.getString("username");
				
				list.add(new Student(sid, cid, username));
			}
		}
		catch (SQLException e) {
			
		}
		
		return list;
	}
	
	
	// Student Methods

	public int getbid(int id) {
		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select bid,seats from batch where cid=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getInt("seats") > 0) {
					
					int batch= rs.getInt("bid");
					
					PreparedStatement ps2=conn.prepareStatement("update batch set seats=seats-1 where bid="+batch+"");
					ps2.execute();
					return batch;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String AddStudent(Student stu) {
		String message = "Not Inserted...";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into student(cid,username,password) values(?,?,?)");

			ps.setInt(1, stu.getCid());
			ps.setString(2, stu.getUsername());
			ps.setString(3, stu.getPass());

			int x = ps.executeUpdate();

			if (x > 0) {
				message = "1 line inserted";
			}
		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String updateStudent(int sid) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
