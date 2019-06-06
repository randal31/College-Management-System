package model;

import java.io.IOException;

public class Demo {

	public static void main(String [] args) throws IOException {
		
		final int CLASSROOM_SIZE = 50;
		final int FACULTY_SIZE = 50;
		//final int TEXTBOOK_SIZE = 50;
		//final int STUDENT_SIZE = 50;

		/*for (int i = 0; i < STUDENT_SIZE; i++) {
			Student student = new Student();
			//student.display(student);
			System.out.println(student);
			String description = 
					student.getMiniStudentCourseBag().getMiniStudentInfo()[i].getCourseDescription();
			System.out.println(description);
		}*/
		
		for (int i = 0; i < FACULTY_SIZE; i++) {
			Faculty faculty = new Faculty();
			faculty.display(faculty);
		}
		
		ClassroomBag classroomBag = new ClassroomBag(CLASSROOM_SIZE);
		for (int i = 0; i < CLASSROOM_SIZE; i++) {
			Classroom classroom = new Classroom();
			classroomBag.insert(classroom);
		}
		classroomBag.display();
	}
}