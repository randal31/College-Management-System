package model;

import java.io.FileNotFoundException;

public class DemoInterface {

	public static void main(String[] args) throws FileNotFoundException {
		final int FACULTY_SIZE = 20;
		final int STUDENT_SIZE = 200;
		final int COURSE_SIZE = 1000;
		
		CourseBag courseBag = new CourseBag(COURSE_SIZE);
		
		for (int i = 0; i < COURSE_SIZE; i++) {
			Course course = new Course();
			courseBag.insert(course);
		}
		
		ChargeBag studentCharge = new ChargeBag(STUDENT_SIZE);

		for (int i = 0; i < STUDENT_SIZE; i++) {
			Student student = new Student();
			student.write(student, null, (i == 0) ? true : false);
			studentCharge.insert(student);
		}
		
		ChargeBag facultyCharge = new ChargeBag(FACULTY_SIZE);
		
		for (int i = 0; i < FACULTY_SIZE; i++) {
			Faculty faculty = new Faculty();
			faculty.display(faculty);
			facultyCharge.insert(faculty);
		}
		
		System.out.println("\nstudent charge total: " + String.format("$%,.2f", studentCharge.ChargeTotalAmount()));
		System.out.println("faculty charge total: " + String.format("$%,.2f", facultyCharge.ChargeTotalAmount()));
	}
}