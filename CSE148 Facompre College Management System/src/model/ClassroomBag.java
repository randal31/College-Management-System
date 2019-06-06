package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class ClassroomBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7450617599336974028L;
	private Classroom [] classroomArray;
	private int nElems;
	
	public ClassroomBag(int maxSize) {
		classroomArray = new Classroom[maxSize];
		nElems = 0;
	}
	
	public void insert(Classroom classroom) {
		classroomArray[nElems++] = classroom;
	}
	
	public void display() throws IOException {
		System.out.println("\n-classroom bag-");
		FileWriter fw = new FileWriter("output_data/classrooms.txt");
		PrintWriter classroomWriter = new PrintWriter(fw);
		for (int i = 0; i < nElems; i++) {
			classroomWriter.println(classroomArray[i]);
			System.out.println(classroomArray[i]);
		}
		System.out.println();
		classroomWriter.close();
	}
	
	public Classroom findByRoomNum(String roomNum) {
		for (int i = 0; i < nElems; i++) {
			if (classroomArray[i].getRoomNumber().equals(roomNum)) {
				return classroomArray[i];
			}
		}
		return null;
	}
	
	public Classroom deleteByRoomNum(String roomNum) {
		int i = 0;
		for(i = 0; i < nElems; i++) {
			if(classroomArray[i].getRoomNumber().equals(roomNum)) {
				break;
			}
		}
		if( i == nElems ) {
			return null;
		} else {
			Classroom temp = classroomArray[i];
			for(int j = i; j < nElems-1; j++) {
				classroomArray[j] = classroomArray[j+1]; 
			}
			nElems--;
			return temp;
		}
	}

	public Classroom[] getClassroomArray() {
		return classroomArray;
	}

	public int getnElems() {
		return nElems;
	}
}