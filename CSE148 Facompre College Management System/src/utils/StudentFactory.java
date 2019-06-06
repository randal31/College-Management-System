package utils;

import java.util.Random;

import model.Grade;
import model.Major;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;
import model.StudentStatus;

public class StudentFactory {
	private static final Random RAND = new Random();
	
	public static Major emitMajor() {
		return Major.values()[RAND.nextInt(Major.values().length)];
	}
	
	public static double emitGpa() {
		Grade grade = MiniStudentCourseInfoBagFactory.emitGrade();
		double gpa = MiniStudentCourseInfoBagFactory.getGradePoint(grade);
		return gpa;
	}

	public static MiniStudentCourseBag emitMiniStudentCourseInfoBag() {
		MiniStudentCourseBag miniStudentCourseBag = new MiniStudentCourseBag(1);
		miniStudentCourseBag.insert(emitCourse());
		return miniStudentCourseBag;
	}
	
	public static MiniStudentInfo emitCourse() {
		String courseNumber = CourseFactory.emitCourseNumber();
		String courseTitle = CourseFactory.emitCourseTitle();
		String courseDescription = CourseFactory.emitCourseDescription();
		double numberOfCredits = CourseFactory.emitNumberOfCredits();
		
		Grade letterGrade = MiniStudentCourseInfoBagFactory.emitGrade();
		StudentStatus status = MiniStudentCourseInfoBagFactory.emitStudentStatus();
		
		MiniStudentInfo studentInfo = new MiniStudentInfo(courseNumber, courseTitle, courseDescription, (int) numberOfCredits,
				letterGrade, status);
		return studentInfo;
	}
}