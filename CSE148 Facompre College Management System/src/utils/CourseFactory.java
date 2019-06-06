package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CourseFactory {
	private static Scanner sc;
	private static int lineCounter = 1;
	private static final int FINAL_LINE = 7315;
	
	
	public static String emitCourseNumber() {
		endOfFile();
		final int remainder = 1;
		try {
			sc  = new Scanner(new File("input_data/course_inventory.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < lineCounter; i++) {
			sc.nextLine();
		}
		while (sc.hasNextLine() && lineCounter <= FINAL_LINE) {
			
			if (lineCounter++ % 5 != remainder) {
				sc.nextLine();
				endOfFile();
			}
			else {
				break;
			}
		}
		return sc.nextLine();
	}
	
	public static String emitCourseTitle() {
		final int remainder = 2;
		try {
			sc  = new Scanner(new File("input_data/course_inventory.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < lineCounter; i++) {
			sc.nextLine();
		}
		while (sc.hasNextLine() && lineCounter <= FINAL_LINE) {
			if (lineCounter++ % 5 != remainder) {
				sc.nextLine();
				endOfFile();
			}
			else {
				break;
			}
		}
		return sc.nextLine();
	}
	
	public static String emitCourseDescription() {
		final int remainder = 3;
		try {
			sc  = new Scanner(new File("input_data/course_inventory.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < lineCounter; i++) {
			sc.nextLine();
		}
		while (sc.hasNextLine() && lineCounter <= FINAL_LINE) {
			if (lineCounter++ % 5 != remainder) {
				sc.nextLine();
				endOfFile();
			}
			else {
				break;
			}
		}
		return sc.nextLine();
	}
	
	public static double emitNumberOfCredits() {
		final int remainder = 4;
		try {
			sc  = new Scanner(new File("input_data/course_inventory.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < lineCounter; i++) {
			sc.nextLine();
		}
		while (sc.hasNextLine() && lineCounter <= FINAL_LINE) {
			if (lineCounter++ % 5 != remainder) {
				sc.nextLine();
				endOfFile();
			}
			else {
				break;
			}
		}
		return sc.nextDouble();
	}
	
	private static void endOfFile() {
		if (lineCounter >= FINAL_LINE) {
			lineCounter = 1;
		}
	}
}