package config;

public class BagConfiguration {
	private static final int MAX_PERSON = 3000;
	private static final int MAX_TEXTBOOK = 1000;
	private static final int MAX_CLASSROOM = 150;
	private static final int MAX_COURSE = 1000;
	
	public static int getMaxPerson() {
		return MAX_PERSON;
	}
	public static int getMaxTextbook() {
		return MAX_TEXTBOOK;
	}
	public static int getMaxClassroom() {
		return MAX_CLASSROOM;
	}
	public static int getMaxCourse() {
		return MAX_COURSE;
	}
}