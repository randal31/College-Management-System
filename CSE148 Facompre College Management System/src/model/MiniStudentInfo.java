package model;

import java.io.Serializable;

import utils.MiniStudentCourseInfoBagFactory;

public class MiniStudentInfo extends Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7487834586553428440L;
	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private int numberOfCredits;
	private Grade letterGrade;
	private StudentStatus studentStatus;
	
	public MiniStudentInfo() {
		courseNumber = MiniStudentCourseInfoBagFactory.emitCourseNumber();
		courseTitle = MiniStudentCourseInfoBagFactory.emitCourseTitle();
		courseDescription = MiniStudentCourseInfoBagFactory.emitCourseDescription();
		numberOfCredits = (int) MiniStudentCourseInfoBagFactory.emitNumberOfCredits();
		studentStatus = MiniStudentCourseInfoBagFactory.emitStudentStatus();
		letterGrade = MiniStudentCourseInfoBagFactory.getRandGrade();
	}
	
	public MiniStudentInfo(String courseNumber, String courseTitle, String courseDescription, int numberOfCredits,
			Grade letterGrade, StudentStatus studentStatus) {
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = letterGrade;
		this.studentStatus = studentStatus;
	}
	
	public String getCourseTitle() {
		return courseTitle;
	}
	
	public String getCourseDescription() {
		return courseDescription;
	}

	public Grade getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(Grade letterGrade) {
		this.letterGrade = letterGrade;
	}

	@Override
	public String toString() {
		return "MiniStudentInfo [course number: " + courseNumber + ", number of credits: " + numberOfCredits
				+ ",\nletter grade: " + letterGrade + ", course status: " + studentStatus + "]";
	}
}