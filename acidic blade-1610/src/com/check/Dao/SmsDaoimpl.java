package com.check.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public String UpdateFee(int cid,int newfee) {
		String message = "Not Changed";

		try (Connection conn = Server.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(" update course set fee=? where cid=?");

			ps.setInt(1, newfee);
			ps.setInt(2, cid);
			
			int x = ps.executeUpdate();
			if(x>0) message="Fees Updated";

		} catch (SQLException e) {
			message=e.getMessage();
		}

		return message;
	}

}
