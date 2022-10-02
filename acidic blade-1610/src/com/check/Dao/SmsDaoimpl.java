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
import com.mysql.cj.xdevapi.Result;

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

	public List<Integer> avail_student(){
		List<Integer> list=new ArrayList<>();
		
		try(Connection conn=Server.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("Select sid from student where bid is null");
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("sid"));
			}
		}
		catch(SQLException e) {
			
		}
		
		return list;
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
			message = "Duplicate Course....";
		}

		return message;
	}

	public List<Batch> showBatch() {
		List<Batch> list = new ArrayList<>();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select cid,bid from batch");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("cid");
				int bid = rs.getInt("bid");
				list.add(new Batch(bid, cid));
			}
		} catch (SQLException e) {

		}

		return list;
	}

	public List<Course> showCourses() {

		List<Course> list = new ArrayList<>();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select cid,cname from course");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("cid");
				String name = rs.getString("cname");
				list.add(new Course(id, name));
			}
		} catch (SQLException e) {

		}
		return list;
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

	public List<Integer> findBatches(int sid) {
		List<Integer> list = new ArrayList<>();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps1 = conn.prepareStatement("select cid from student where sid=" + sid + "");
			ResultSet rs2 = ps1.executeQuery();

			if (rs2.next())
				sid = rs2.getInt("cid");

			PreparedStatement ps = conn.prepareStatement("select bid,seats from batch where cid=?");

			ps.setInt(1, sid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getInt("seats") > 0) {
					list.add(rs.getInt("bid"));
				}
			}

		} catch (SQLException e) {

		}

		return list;
	}

	@Override
	public String AllocateBatch(int sid, int bid) {
		String message = "No batch Found";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update student set bid=? where sid=?");
			ps.setInt(1, bid);
			ps.setInt(2, sid);

			int x = ps.executeUpdate();
			if (x > 0) {
				getbid(bid);
				message = "Batch Allocated is " + bid;
			}
		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String UpdateSeats(int bid, int newseats) {
		String message = "Seats Not updated";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update batch set seats=? where bid=?");

			ps.setInt(1, newseats);
			ps.setInt(2, bid);
			if (ps.executeUpdate() > 0) {
				message = "Seats Updated";
			}
		} catch (SQLException e) {
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public List<Student> ViewStudent(int bid) {
		List<Student> list = new ArrayList<>();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from student where bid=?");
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int sid = rs.getInt("sid");
				int cid = rs.getInt("cid");
				String username = rs.getString("username");

				list.add(new Student(sid, cid, username));
			}
		} catch (SQLException e) {

		}

		return list;
	}

	// Student Methods

	public void getbid(int id) {
		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps2 = conn.prepareStatement("update batch set seats=seats-1 where bid=" + id + "");
			ps2.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
				message = "Student added...!";
			}
		} catch (SQLException e) {
			message = "Name already Exist";
		}

		return message;
	}

	public int checkStudent(String user, String pass) {

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select sid from student where username=? and password=?");

			ps.setString(1, user);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("sid");

		} catch (SQLException e) {

		}

		return 0;
	}

	@Override
	public String updateStudent(int sid, String change, int choice) {
		String message = "Value not changed..";

		try (Connection conn = Server.provideConnection()) {
			PreparedStatement ps = null;
			if (choice == 1) {
				ps = conn.prepareStatement("update student set sname=? where sid=?");
			} else if (choice == 2) {
				ps = conn.prepareStatement("update student set username=? where sid=?");
			} else {
				ps = conn.prepareStatement("update student set password=? where sid=?");
			}
			ps.setString(1, change);
			ps.setInt(2, sid);

			int x = ps.executeUpdate();
			if (x > 0)
				message = "Value Updated";
		} catch (SQLException e) {
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public List<Course> seeAllCourse() {
		List<Course> list = new ArrayList<>();

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select c.cid,c.fee,c.cname,sum(b.seats) from course c inner join batch b on c.cid=b.cid group by cid;");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("c.cid");
				int fee = rs.getInt("c.fee");
				String name = rs.getString("c.cname");
				int seats = rs.getInt("sum(b.seats)");
				list.add(new Course(id, name, fee, seats));
			}

		} catch (SQLException e) {

		}

		return list;
	}

}
