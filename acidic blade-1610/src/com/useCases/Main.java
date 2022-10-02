package com.useCases;

import java.util.InputMismatchException;
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

			Scanner sc = new Scanner(System.in);
			int choice;
			try {
			 choice = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid choice ");
				 choice =0;
			}
			SMSDao sd = new SmsDaoimpl();
			switch (choice) {
			case 0: 
				break;
			case 1: {
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");

				try {
					
					System.out.println("Enter course Id");
					int id = sc.nextInt();

					System.out.println("Enter Course name");
					String cname = sc.next();

					System.out.println("Enter Course fee");
					int fee = sc.nextInt();

					String message = sd.AddCourse(id, cname, fee);
					System.out.println(message);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");

				}
			}
				break;
			case 2: {
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");
				try {
					System.out.println("Enter course Id");
					int id = sc.nextInt();

					System.out.println("Enter New fees");
					int fee = sc.nextInt();

					String message = sd.UpdateFee(id, fee);
					System.out.println(message);

				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 3: {
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");

				try {
					System.out.println("Enter course Id you want to delete");
					int id = sc.nextInt();

					String mess = sd.DeleteCourse(id);
					System.out.println(mess);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 4: {
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");

				System.out.println("Enter course Id");

				try {
					int cid = sc.nextInt();
					Course c1 = sd.searchcourse(cid);
					if (c1.getCid() == 0) {
						System.out.println("No Course found...");
						break;
					}
					System.out.println("Course Id: " + c1.getCid());
					System.out.println("Course Name: " + c1.getName());
					System.out.println("Course Fee: " + c1.getFee());
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 5: {
				List<Batch> blist = new SmsDaoimpl().showBatch();
				System.out.println("Available Batches");
				blist.forEach(s -> {
					System.out.println("Batch Id:-" + s.getBatchId() + "- CourseId:-" + s.getCourseId());
				});
				System.out.println("=================================");
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");

				try {
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
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 6: {
				
				SmsDaoimpl nsd = new SmsDaoimpl();
				 List<Integer> li= nsd.avail_student();
				 
				if(li.size()==0) {
					System.out.println("No student available to assign");
					break;
				}
				System.out.println("Student Available");
				li.forEach(s->{
					System.out.print(s+" ");
				});
				System.out.println();
				System.out.println("Enter Student Id");
				int sid = sc.nextInt();
				
				List<Integer> blist = nsd.findBatches(sid);

				if (blist.size() == 0) {
					System.out.println("No batch for this Course ");
					break;
				}
				System.out.println("Batches Available: ");
				blist.forEach(s -> {
					System.out.print(s + " ");
				});
				System.out.println();

				try {
					System.out.println("Select a Batch:-");
					int bid = sc.nextInt();

					String message = sd.AllocateBatch(sid, bid);
					System.out.println(message);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;

			case 7: {
				List<Batch> blist = new SmsDaoimpl().showBatch();
				System.out.println("Available Batches");
				blist.forEach(s -> {
					System.out.println("Batch Id:-" + s.getBatchId() + "- CourseId:-" + s.getCourseId());
				});
				System.out.println("=================================");

				try {
					System.out.println("Enter batch id");
					int bid = sc.nextInt();

					System.out.println("Enter New available seats");
					int seats = sc.nextInt();

					String message = sd.UpdateSeats(bid, seats);
					System.out.println(message);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 8: {
				List<Batch> blist = new SmsDaoimpl().showBatch();
				System.out.println("Available Batches");
				blist.forEach(s -> {
					System.out.println("Batch Id:-" + s.getBatchId() + "- CourseId:-" + s.getCourseId());
				});
				System.out.println("=================================");

				try {
					System.out.println("Enter batch Id");
					int bid = sc.nextInt();

					List<Student> li = sd.ViewStudent(bid);

					if (li.size() == 0) {
						System.out.println("No Student In this course");
						break;
					}

					for (int i = 0; i < li.size(); i++) {
						Student s = li.get(i);
						System.out.println("Student Details: " + (i + 1));
						System.out.println("Id: " + s.getSid());
						System.out.println("Course Id: " + s.getCid());
						System.out.println("Username: " + s.getUsername());
						System.out.println("--------------------------");

					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
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

		while (true) {
			System.out.println("Select yor choice");
			System.out.println("==========================");
			System.out.println("1. Register Yourself");
			System.out.println("2. Update Details");
			System.out.println("3. See course list and seats available");

			Scanner sc = new Scanner(System.in);
			
			int choice = sc.nextInt();
			SMSDao sd = new SmsDaoimpl();
			switch (choice) {

			case 1: {
				List<Course> list = new SmsDaoimpl().showCourses();
				System.out.println("Available Courses");
				list.forEach(s -> {
					System.out.println(s.getCid() + "-" + s.getName());
				});
				System.out.println("=================================");
				try {
					
					
					System.out.println("Enter Course Id");
					int id = sc.nextInt();

					System.out.println("Enter username");
					String username = sc.next();

					System.out.println("Enter password");
					String pass = sc.next();

					Student stu = new Student(id, username, pass);
					String message = sd.AddStudent(stu);
					System.out.println(message);
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input...");
				}
			}
				break;
			case 2: {
				System.out.println("Enter username: ");
				String username = sc.next();

				System.out.println("Enter password: ");
				String password = sc.next();
				SmsDaoimpl nsd = new SmsDaoimpl();
				int sid = nsd.checkStudent(username, password);
				if (sid == 0) {
					System.out.println("Wrong username or password");
					break;
				}
				System.out.println("----------Welcome----------");
				System.out.println("Do you want to change:- ");
				System.out.println("1.Name");
				System.out.println("2.Username");
				System.out.println("3.password");
				int choose = sc.nextInt();
				String message = "";
				if (choose == 1) {
					System.out.println("Enter name");
					String name = sc.next();
					message = sd.updateStudent(sid, name, 1);
				} else if (choice == 2) {
					System.out.println("Enter Username");
					String user = sc.next();
					message = sd.updateStudent(sid, user, 2);
				} else if (choice == 3) {
					System.out.println("Enter Password");
					String pass = sc.next();
					message = sd.updateStudent(sid, pass, 3);
				} else {
					message = "Invalid choice";
				}
				System.out.println(message);
			}
				break;
			case 3: {

				List<Course> lc = sd.seeAllCourse();

				for (int i = 0; i < lc.size(); i++) {
					Course c = lc.get(i);
					System.out.println("Course list:- " + (i + 1));
					System.out.println("--------------------------");
					System.out.println("Course id :" + c.getCid());
					System.out.println("Course Name: " + c.getName());
					System.out.println("Course fee: " + c.getFee());
					System.out.println("Course Seats: " + c.getSeats());
					System.out.println("--------------------------");
				}
			}
				break;
			default: {
				System.out.println("Thank you...!");
				return;
			}
			}

		}

	}

	public static void main(String[] args) {

		while (true) {
			System.out.println("----------------------------------------------");
			System.out.println("Welcome to Automated Student management System");
			System.out.println("----------------------------------------------");
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
