package utils;

public class IdFactory {
	
	public static String emitID(String rawID, String fillIn) {
		String id = rawID;
		final int ID_LENGTH = 8;
		for (int i = rawID.length(); i < ID_LENGTH; i++) {
			id = fillIn + id;
		}
		return id;
	}
}