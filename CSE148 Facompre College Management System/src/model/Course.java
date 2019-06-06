package model;

import java.io.Serializable;

import utils.CourseFactory;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8696512308415622032L;
	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private double numberOfCredits;
	
	public Course() {
		courseNumber = courseCheck(CourseFactory.emitCourseNumber());
		courseTitle = courseCheck(CourseFactory.emitCourseTitle());
		courseDescription = courseCheck(CourseFactory.emitCourseDescription());
		numberOfCredits = CourseFactory.emitNumberOfCredits();
	}
	
	private String courseCheck(String courseData) {
		if (courseData.equals("")) {
			courseData = "N/A";
		}
		return courseData;
	}
	
	public String getCourseTitle() {
		return courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public String getCourseNumber() {
		return courseNumber;
	}
	
	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	@Override
	public String toString() {
		return "Course [course number: " + courseNumber + ", courseTitle: " + courseTitle 
				+ ", number of credits: " + numberOfCredits + "]";
	}
}