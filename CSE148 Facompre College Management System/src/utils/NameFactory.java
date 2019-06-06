package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class NameFactory {
	
	private static Scanner sc;
	private static boolean isFound = true;
	private static final Random RAND = new Random();
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 2000;
	
	public static String emitFirstName(int lineCounter, boolean isStudent) {
		File txtfile;
		String firstName = "";
		final int RAND_TXT = RAND.nextInt((MAX_NUMBER - MIN_NUMBER) + 1) + MIN_NUMBER;
		try {
			if (isStudent) {
				if (RAND_TXT > 1000) {
					txtfile = new File("input_data/boys_names.txt");
					firstName = emitRandName(txtfile, true, true);
				}
				else {
					txtfile = new File("input_data/girls_names.txt");
					firstName = emitRandName(txtfile, true, false);
				}
				
			}
			else {
				txtfile = new File("input_data/First_Names.txt");	
				firstName = emitText(txtfile, lineCounter);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return firstName;
	}
	
	public static String emitLastName(int lineCounter, boolean isStudent) {
		String lastName = "";
		File txtfile = new File("input_data/Last_Names.txt");
		try {
			if (isStudent) {
				lastName = emitRandName(txtfile, false, false);
			}
			else {
				lastName = emitText(txtfile, lineCounter);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lastName;
	}
	
	public static String emitRandName(File txtfile, boolean isFirstName, boolean isBoy) 
			throws FileNotFoundException {
		Scanner sc = new Scanner(txtfile);
		String [] nameArray = new String [MAX_NUMBER];
		int randNum;
		if (isFirstName) {
			if (isBoy) {
				nameArray = String.valueOf(sc.nextLine()).split("\t");
				String [] nameArrayFinal = new String[nameArray.length];
				for (int i = 0; i < nameArray.length; i++) {
					String[] tokens = nameArray[i].split(" ");
					nameArrayFinal[i] = tokens[1];
				}
				randNum = RAND.nextInt(nameArrayFinal.length);
				sc.close();
				return nameArrayFinal[randNum];
			}
			else {
				nameArray = String.valueOf(sc.nextLine()).split("[ \t]");
				randNum = RAND.nextInt(nameArray.length);
				sc.close();
				return nameArray[randNum];
			}	
		}
		else {
			for (int i = 0; sc.hasNextLine(); i++) {
				nameArray[i] = String.valueOf(sc.next());
			}
			randNum = RAND.nextInt((MAX_NUMBER - MIN_NUMBER) - 1) + MIN_NUMBER;
			sc.close();
			return nameArray[randNum];
		}
	}
	
	public static String emitText(File txtfile, int lineCounter) {
		try {
			sc = new Scanner(txtfile);
			findLine(lineCounter);
			return sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
			isFound = false;
		}
		return null;
	}
	
	private static void findLine(int lineCounter) {
		for (int i = 0; i < lineCounter; i++) {
			sc.nextLine();
		}
	}

	public static boolean isFound() {
		return isFound;
	}
}