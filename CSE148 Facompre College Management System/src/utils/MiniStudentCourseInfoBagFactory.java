package utils;

import java.util.Random;
import model.Grade;
import model.StudentStatus;

public class MiniStudentCourseInfoBagFactory {
	private static final Random RAND = new Random();
	
	private static Grade randGrade;
	
	public static StudentStatus emitStudentStatus() {
		StudentStatus randStatus = StudentStatus.values()[RAND.nextInt(StudentStatus.values().length)];
		return randStatus;
	}
	
	public static String emitCourseNumber() {
		String courseNumber = null;
		while (courseNumber == null) {
			courseNumber = CourseFactory.emitCourseNumber();
		}
		return courseNumber;
	}
	
	public static String emitCourseTitle() {
		String courseTitle = null;
		while (courseTitle == null) {
			courseTitle = CourseFactory.emitCourseTitle();
		}
		return courseTitle;
	}
	
	public static String emitCourseDescription() {
		String courseTitle = null;
		while (courseTitle == null) {
			courseTitle = CourseFactory.emitCourseDescription();
		}
		return courseTitle;
	}
	
	public static double emitNumberOfCredits() {
		double numberOfCredits = CourseFactory.emitNumberOfCredits();
		return numberOfCredits;
	}
	
	public static Grade emitGrade() {
		randGrade =  Grade.values()[RAND.nextInt(Grade.values().length)];
		return randGrade;
	}
	
	public static Grade getRandGrade() {
		return randGrade;
	}
	
	public static Grade getLetterGrade(double gpa) {
		if (gpa == 4.0) {
			return Grade.A;
		}
		else if (gpa >= 3.5) {
			return Grade.B_PLUS;
		}
		else if (gpa >= 3.0) {
			return Grade.B;
		}
		else if (gpa >= 2.5) {
			return Grade.C_PLUS;
		}
		else if (gpa >= 2.0) {
			return Grade.C;
		}
		else if (gpa >= 1.5) {
			return Grade.D_PLUS;
		}
		else if (gpa >= 1.0) {
			return Grade.D;
		}
		else {
			return Grade.F;
		}
	}

	public static double getGradePoint(Grade grade) {
		if (grade == Grade.A) {
			return 4.0;
		}
		else if (grade == Grade.B_PLUS) {
			return 3.5;
		}
		else if (grade == Grade.B) {
			return 3.0;
		}
		else if (grade == Grade.C_PLUS) {
			return 2.5;
		}
		else if (grade == Grade.C) {
			return 2.0;
		}
		else if (grade == Grade.D_PLUS) {
			return 1.5;
		}
		else if (grade == Grade.D) {
			return 1.0;
		}
		else {
			return 0.0;
		}
	}
}