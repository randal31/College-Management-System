package model;

import java.io.Serializable;

import utils.MiniFacultyCourseInfoBagFactory;

public class MiniFacultyInfo extends Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -821747673227923553L;
	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private int numberOfCredits;
	private TeacherStatus courseStatus;
	
	public MiniFacultyInfo() {
		super();
		courseNumber = MiniFacultyCourseInfoBagFactory.emitCourseNumber();
		courseTitle = String.valueOf(MiniFacultyCourseInfoBagFactory.emitTitle());
		numberOfCredits = (int) MiniFacultyCourseInfoBagFactory.emitNumberOfCredits();
		courseStatus = MiniFacultyCourseInfoBagFactory.emitTeacherStatus();
	}
	
	public MiniFacultyInfo(String courseNumber, String courseTitle, String courseDescription, int numberOfCredits,
			TeacherStatus courseStatus) {
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.numberOfCredits = numberOfCredits;
		this.courseStatus = courseStatus;
	}
	
	public String getCourseDescription() {
		return courseDescription;
	}

	@Override
	public String toString() {
		return "MiniFacultyInfo [course number: " + courseNumber + ", course title: " + courseTitle + ",\nnumber of credits: "
				+ numberOfCredits + ", course status: " + courseStatus + "]";
	}
}