package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;

import config.CharLimit;
import utils.FacultyFactory;

public class Faculty extends Person implements FMSS, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5846481667093261430L;
	private Title title;
	private Major major;
	private double salary;
	private String officePhone;
	private MiniFacultyCourseBag miniFacultyCourseBag;
	
	private String areaCode;
	private static double sumCharge = 0.0;
	
	private static int phoneNumberCounter = 0;
	private static boolean newFile = true;
	private static final Random RAND = new Random();
	public Faculty() {
		super();
		title = FacultyFactory.emitTitle();
		major = FacultyFactory.emitMajor();
		salary = FacultyFactory.emitSalary();
		areaCode = randAreaCode();
		officePhone = FacultyFactory.emitPhoneNumber(String.valueOf(phoneNumberCounter++),
				areaCode);
		miniFacultyCourseBag = FacultyFactory.emitMiniFacultyCourseInfoBag();
		sumCharge += chargeTotal(sumCharge, chargeFood());
		sumCharge += chargeTotal(sumCharge, chargeParking());
		sumCharge += chargeTotal(sumCharge, chargeOffice(salary));
	}
	
	public Faculty(String firstName, String lastName, String id, String officePhone,
			double salary, Title title, Major major, MiniFacultyCourseBag miniFacultyCourseBag,
			boolean isUpdate) {
		super(firstName, lastName, id, isUpdate);
		this.officePhone = officePhone;
		this.salary = salary;
		this.title = title;
		this.major = major;
		this.miniFacultyCourseBag = miniFacultyCourseBag;
	}

	public void display(Faculty faculty) {
		try {
			File fw = new File("output_data/faculty.txt");
			if (newFile) {
				PrintWriter facultyRefresh = new PrintWriter(fw);
				facultyRefresh.close();
				newFile = false;
			}
			PrintWriter facultyWriter = new PrintWriter(new FileWriter(fw, true));
			facultyWriter.append(faculty.toString() + "\n");
			facultyWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Title getTitle() {
		return title;
	}

	public Major getMajor() {
		return major;
	}

	public double getSalary() {
		return salary;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}

	public String randAreaCode() {
		String areaCode = "";
		for (int i = 0; i < CharLimit.getAreaCode(); i++) {
			areaCode += String.valueOf(RAND.nextInt(10));
		}
		return areaCode;
	}

	public static double getSumCharge() {
		return sumCharge;
	}

	@Override
	public String toString() {
		return "Faculty [" + super.toString() + ",\ntitle: " + title + ", major: " + major + ", salary: " + String.format("$%,.2f", salary) + ", officePhone: " 
				+ officePhone + ",\n" + miniFacultyCourseBag + "]";
	}

	@Override
	public double chargeFood() {
		final double FOOD_CHARGE = 20.0;
		return FOOD_CHARGE;
	}

	@Override
	public double chargeTotal(double value, double add) {
		return value + add;
	}
}