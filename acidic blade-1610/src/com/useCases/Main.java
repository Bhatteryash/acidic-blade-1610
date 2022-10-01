package com.useCases;

import java.util.List;
import java.util.Scanner;

import com.beanclass.Batch;
import com.beanclass.Course;
import com.beanclass.Student;
import com.check.Dao.SMSDao;
import com.check.Dao.SmsDaoimpl;
import com.exception.Incorrectvalues;

public class Main {

	public static void adminAccess() {

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Select your choice");
			System.out.println("==========================");

			System.out.println("1. Add a new Courses");
			System.out.println("2. Update Fees of course.");
			System.out.println("3. Delete  a course from any Training session.");
			System.out.println("4. Search information about a course.");
			System.out.println("5. Create Batch under a course.");
			System.out.println("6. Allocate students in a Batch under a course.");
			System.out.println("7. Update total seats of a batch.");
			System.out.println("8. View the students of every batch. ");
			System.out.println("Choose any other key to exit.");

			int choice = sc.nextInt();
			SMSDao sd = new SmsDaoimpl();
			switch (choice) {
			case 1: {
				System.out.println("Enter course Id");
				int id = sc.nextInt();

				System.out.println("Enter Course name");
				String cname = sc.next();

				System.out.println("Enter Course fee");
				int fee = sc.nextInt();

				String message = sd.AddCourse(id, cname, fee);
				System.out.println(message);
			}
				break;
			case 2: {

				System.out.println("Enter course Id");
				int id = sc.nextInt();

				System.out.println("Enter New fees");
				int fee = sc.nextInt();

				String message = sd.UpdateFee(id, fee);
				System.out.println(message);

			}
				break;
			case 3: {
				System.out.println("Enter course Id you want to delete");
				int id = sc.nextInt();

				String mess = sd.DeleteCourse(id);
				System.out.println(mess);
			}
				break;
			case 4: {
				System.out.println("Enter course Id");
				int cid = sc.nextInt();
				Course c1 = sd.searchcourse(cid);
				if (c1.getCid() == 0) {
					System.out.println("No Course found...");
					break;
				}
				System.out.println("Course Id: " + c1.getCid());
				System.out.println("Course Name: " + c1.getName());
				System.out.println("Course Fee: " + c1.getFee());
			}
				break;
			case 5: {
				System.out.println("Enter Batch Id");
				int bid = sc.nextInt();

				System.out.println("Enter Course Id");
				int cid = sc.nextInt();

				System.out.println("Number of seats");
				int seats = sc.nextInt();

				System.out.println("Enter Timing (m-morning || e-evening)");
				String slot = sc.next();

				String message = sd.CreateBatch(new Batch(bid, cid, seats, slot));
				System.out.println(message);
			}
				break;
			case 6: {
				System.out.println("Enter Student Id");
				int sid=sc.nextInt();
				
				String message= sd.AllocateBatch(sid);
				System.out.println(message);
			}
				break;

			case 7: {
				System.out.println("Enter batch id");
				int bid=sc.nextInt();
				
				System.out.println("Enter New available seats");
				int seats=sc.nextInt();
				
				String message= sd.UpdateSeats(bid, seats);
				System.out.println(message);
			}
				break;
			case 8: {
				System.out.println("Enter batch Id");
				int bid=sc.nextInt();
				
				List<Student> li= sd.ViewStudent(bid);
				for(int i=0;i<li.size();i++) {
					Student s=li.get(i);
					System.out.println("Student Details: "+(i+1));
					System.out.println("Id: "+s.getSid());
					System.out.println("Course Id: "+s.getCid());
					System.out.println("Username: "+s.getUsername());
					System.out.println("--------------------------");
					
				}
			}
				break;
			default:
				System.out.println("Thank you...!");
				return;
			}
		}
	}

	public static void StudentAccess() {

		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("Select yor choice");
			System.out.println("==========================");
			System.out.println("1. Register Yourself");
			System.out.println("2. Update Details");
			System.out.println("3. See course list and seats available");

			int choice = sc.nextInt();
			SMSDao sd = new SmsDaoimpl();
			switch (choice) {

			case 1: {

				System.out.println("Enter Course Id");
				int id = sc.nextInt();

				System.out.println("Enter username");
				String username = sc.next();

				System.out.println("Enter password");
				String pass = sc.next();

				Student stu = new Student(id, username, pass);
				String message = sd.AddStudent(stu);
				System.out.println(message);
			}
				break;
			case 2: {
				
				
				
			}
				break;
			case 3: {

			}
				break;
			default: {

			}
			}

		}

	}

	public static void main(String[] args) {

		while (true) {
			System.out.println("Welcome to Automated Student management System");
			System.out.println("Login as ");
			System.out.println("1.Admin");
			System.out.println("2.Student");
			System.out.println("Select any other number to exit Database..!");

			Scanner sc = new Scanner(System.in);

			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Enter your username");
				String user = sc.next();

				System.out.println("Enter your password");
				String pass = sc.next();

				SMSDao sd = new SmsDaoimpl();
				try {
					boolean bl = sd.authenticateAdmin(user, pass);
					if (bl) {
						System.out.println("Welcome " + user);
						Main.adminAccess();
					} else
						System.out.println("Incorrect username or password try Again");
				} catch (Incorrectvalues e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}

			} else if (choice == 2) {
				Main.StudentAccess();
			} else {
				System.out.println("Thank you for using our service!!");
				break;
			}
		}

	}

}
