package com.useCases;

import java.util.Scanner;
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

			switch (choice) {
			case 1: {
				System.out.println("Enter course Id");
				int id = sc.nextInt();

				System.out.println("Enter Course name");
				String cname = sc.next();

				System.out.println("Enter Course fee");
				int fee = sc.nextInt();

				SMSDao sd = new SmsDaoimpl();
				String message = sd.AddCourse(id, cname, fee);
				System.out.println(message);
			}
				break;
			case 2: {
				
				System.out.println("Enter course Id");
				int id=sc.nextInt();
				
				System.out.println("Enter New fees");
				int fee=sc.nextInt();
				
				SMSDao sd=new SmsDaoimpl();
				String message= sd.UpdateFee(id, fee);
				System.out.println(message);
				
			}
				break;
			case 3: {

			}
				break;
			case 4: {

			}
				break;
			case 5: {

			}
				break;
			case 6: {

			}
				break;

			case 7: {

			}
				break;
			case 8: {

			}
				break;
			default:
				System.out.println("Thank you...!");
				return;
			}
		}
	}

	public static void StudentAccess() {

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

			} else {
				System.out.println("Thank you for using our service!!");
				break;
			}
		}

	}

}
