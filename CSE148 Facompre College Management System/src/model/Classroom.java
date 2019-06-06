package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javafx.scene.control.Alert;
import utils.ClassroomFactory;

public class Classroom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 77962684427751529L;
	private String roomNumber;
	private Building roomBuilding;
	
	private static int roomCounter = 1;

	public Classroom() {
		roomBuilding = ClassroomFactory.emitBuilding();
		roomNumber = String.valueOf(roomBuilding).substring(0, 1) 
				+ ClassroomFactory.emitRoomNumber(String.valueOf(roomCounter++));
	}

	public Classroom(Building roomBuilding, String roomNumber, boolean isUpdate) {
		this.roomBuilding = roomBuilding;
		this.roomNumber = roomNumber;
		if (!isUpdate) {
			roomCounter++;
		}
	}

	public void write(Classroom classroom, Alert errorMsg, boolean newFile) {
		File fw = new File("output_data/classrooms.txt");
		try {
			if (newFile) {
				PrintWriter classroomRefresh = new PrintWriter(fw);
				classroomRefresh.close();
			}
			PrintWriter classroomWriter = new PrintWriter(new FileWriter(fw, true));
			classroomWriter.write(classroom.toString() + "\n");
			classroomWriter.close();
		} catch (IOException e) {
			errorMsg.setTitle("IOException found");
			errorMsg.setHeaderText("IOException thrown on " + fw.getName());
			errorMsg.showAndWait();
		}
	}
	
	public static int getRoomCounter() {
		return roomCounter;
	}

	public static void setRoomCounter(int roomCounter) {
		Classroom.roomCounter = roomCounter;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public Building getRoomBuilding() {
		return roomBuilding;
	}

	@Override
	public String toString() {
		return "Classroom [roomNumber=" + roomNumber + ", roomBuilding=" + roomBuilding + "]";
	}
}