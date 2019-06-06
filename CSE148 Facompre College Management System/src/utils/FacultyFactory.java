package utils;

import java.util.Random;

import config.CharLimit;
import model.Major;
import model.MiniFacultyCourseBag;
import model.MiniFacultyInfo;
import model.TeacherStatus;
import model.Title;

public class FacultyFactory {

	private static final Random RAND = new Random();
	private static final double MIN_SALARY = 10000.0;
	private static final double MAX_SALARY = 100000.0;
	
	private static double salary;
	public static Major emitMajor() {
		return Major.values()[RAND.nextInt(Major.values().length)];
	}
	
	public static Title emitTitle() {
		return Title.values()[RAND.nextInt(Title.values().length)];
	}
	
	public static double emitSalary() {
		salary = (double) (RAND.nextInt((int) ((MAX_SALARY - MIN_SALARY) - 1))) + MIN_SALARY;
		return salary;
	}
	
	public static double getSalary() {
		return salary;
	}

	public static String emitPhoneNumber(String rawPhoneNum, String areaCode) {
		String phoneNum = rawPhoneNum;
		for (int i = rawPhoneNum.length(); i < CharLimit.getPhoneNum(); i++) {
			phoneNum = "?" + phoneNum;
		}
		return "(" + areaCode + ")" + "-" + phoneNum.substring(0, 3) + "-" + phoneNum.substring(3, 7);
	}
	
	public static MiniFacultyCourseBag emitMiniFacultyCourseInfoBag() {
		MiniFacultyCourseBag miniFacultyCourseBag = new MiniFacultyCourseBag(1);
		miniFacultyCourseBag.insert(emitCourse());
		return miniFacultyCourseBag;
	}
	
	public static MiniFacultyInfo emitCourse() {
		String courseNumber = CourseFactory.emitCourseNumber();
		String courseTitle = CourseFactory.emitCourseTitle();
		String courseDescription = CourseFactory.emitCourseDescription();
		double numberOfCredits = CourseFactory.emitNumberOfCredits();
		TeacherStatus status = MiniFacultyCourseInfoBagFactory.emitTeacherStatus();
		
		MiniFacultyInfo facultyInfo = new MiniFacultyInfo(courseNumber, courseTitle,
				courseDescription, (int) numberOfCredits, status);
		return facultyInfo;
	}
}