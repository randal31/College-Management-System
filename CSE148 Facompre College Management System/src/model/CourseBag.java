package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class CourseBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9093489147257428546L;
	private Course [] courseArray;
	private int nElems;
	
	public CourseBag(int maxSize) {
		courseArray = new Course[maxSize];
		nElems = 0;
	}
	
	public void insert(Course course) {
		courseArray[nElems++] = course;
	}
	
	public void display() throws IOException {
		System.out.println("\n-course bag-");
		FileWriter fw = new FileWriter("output_data/courses.txt");
		PrintWriter courseWriter = new PrintWriter(fw);
		for (int i = 0; i < nElems; i++) {
			courseWriter.println(courseArray[i]);
			System.out.println(courseArray[i]);
		}
		System.out.println();
		courseWriter.close();
	}

	public Course[] getCourseArray() {
		return courseArray;
	}
}