package utils;

import java.util.Random;

import model.Building;

public class ClassroomFactory {
	private static final Random RAND = new Random();

	public static Building emitBuilding() {
		Building randBuilding = Building.values()[RAND.nextInt(Building.values().length)];
		return randBuilding;
	}
	
	public static String emitRoomNumber(String rawRoomNum) {
		String roomNum = rawRoomNum;
		final int ROOM_NUM_LENGTH = 3;
		for (int i = rawRoomNum.length(); i < ROOM_NUM_LENGTH; i++) {
			if (roomNum.length() == 2) {
				roomNum = "1" + roomNum;
			}
			else {
				roomNum = "0" + roomNum;
			}	
		}
		return roomNum;
	}
}