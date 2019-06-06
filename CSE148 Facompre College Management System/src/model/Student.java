package model;

import java.io.Serializable;

import utils.NameFactory;
import utils.StudentFactory;

public class Student extends Person implements FMSS, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6532211340926818806L;
	private double gpa;
	private Major major;
	private MiniStudentCourseBag miniStudentCourseBag;
	
	private boolean hasMajor;
	
	public Student() {
		super();
		major = StudentFactory.emitMajor();
		gpa = StudentFactory.emitGpa();
		miniStudentCourseBag = StudentFactory.emitMiniStudentCourseInfoBag();
		if (major == Major.CSE || major == Major.CST) {
			hasMajor = true;
		}
		else {
			hasMajor = false;
		}
	}
	
	public Student(String firstName, String lastName, String id, double gpa, Major major,
			MiniStudentCourseBag miniStudentCourseBag, boolean isUpdate) {
		super(firstName, lastName, id, isUpdate);
		this.gpa = gpa;
		this.major = major;
		this.miniStudentCourseBag = miniStudentCourseBag;
	}
	
	public double getGpa() {
		return gpa;
	}

	public Major getMajor() {
		return major;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	public boolean hasMajor() {
		return hasMajor;
	}

	@Override
	public String toString() {
		if (NameFactory.isFound()) {
			return "Student [" + super.toString() + ",\ngpa: " + gpa + ", major: " + major 
					+ ", " + miniStudentCourseBag + "]";
		}
		else {
			return "";
		}	
	}

	@Override
	public double chargeFood() {
		final double FOOD_CHARGE = 10.0;
		return FOOD_CHARGE;
	}

	@Override
	public double chargeTotal(double value, double add) {
		return value + add;
	}
}