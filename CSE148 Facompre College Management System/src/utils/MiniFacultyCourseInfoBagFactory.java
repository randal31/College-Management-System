package utils;

import java.util.Random;

import model.TeacherStatus;
import model.Title;

public class MiniFacultyCourseInfoBagFactory {
	private static final Random RAND = new Random();
	
	public static TeacherStatus emitTeacherStatus() {
		TeacherStatus randStatus = TeacherStatus.values()[RAND.nextInt(TeacherStatus.values().length)];
		return randStatus;
	}
	
	public static String emitCourseNumber() {
		String courseNumber = null;
		while (courseNumber == null) {
			courseNumber = CourseFactory.emitCourseNumber();
		}
		return courseNumber;
	}
	
	public static double emitNumberOfCredits() {
		double numberOfCredits = CourseFactory.emitNumberOfCredits();
		return numberOfCredits;
	}
	
	public static Title emitTitle() {
		Title randTitle = Title.values()[RAND.nextInt(Title.values().length)];
		return randTitle;
	}
}