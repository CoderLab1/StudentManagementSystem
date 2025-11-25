package school.driver;

import java.util.Scanner;

import school.entity.Student;

public class StudentDriver {

	public static void main(String[] args) {
		StudentService service = new StudentService();

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the our School");

		while (true) {
			System.out.println("press 1 to Add Student");
			System.out.println("press 2 to Update Student");
			System.out.println("press 3 to Fetch Student");
			System.out.println("press 4 to Delete Student");
			System.out.println("press 5 to Exit");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				// call save
				System.out.println("Enter your id");
				int id = sc.nextInt();

				System.out.println("Enter your name");
				String name = sc.next();

				System.out.println("Enter your age");
				int age = sc.nextInt();

				Student st = new Student();
				st.setId(id);
				st.setName(name);
				st.setAge(age);

				int res = service.save(st);
				if (res != 0) {
					System.out.println("Data Added");
				} else
					System.out.println("Data not added!");
				break;

			case 2:
				System.out.println("Enter id to update");
				int idU = sc.nextInt();

				System.out.println("Enter your name");
				String nameU = sc.next();

				System.out.println("Enter your age");
				int ageU = sc.nextInt();

				Student stu = new Student();
				stu.setId(idU);
				stu.setName(nameU);
				stu.setAge(ageU);

				int upd = service.update(stu);
				if (upd != 0)
					System.out.println("Updated Successfully......");
				else
					System.out.println();

				break;
			case 3:
				System.out.println("Enter id to fetch");
				int idF = sc.nextInt();

				Student fetched = service.fetch(idF);
				if (fetched != null) {
					System.out.println("Student found:");
					System.out.println("ID   : " + fetched.getId());
					System.out.println("Name : " + fetched.getName());
					System.out.println("Age  : " + fetched.getAge());
				} else {
					System.out.println("No student found with id: " + idF);
				}
				break;
			case 4:
				System.out.println("Enter id to delete");
				int IdD = sc.nextInt();
				
				int delete = service.delete(IdD);
				
				if(delete!=0) {
					System.out.println("Student Deleted Successfully....");
				}
				
				
				break;
			case 5:
				System.out.println("Exiting... Thankyou");
				sc.close();
				System.exit(0);
				break;

			default:
				System.out.println("Invalid Input");
				break;
			}
		}

	}
}
