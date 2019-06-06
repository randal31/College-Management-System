package config;

public class CharLimit {
	private static final int AREA_CODE = 3;
	private static final int PHONE_NUM = 7;
	private static final int ID_NUMBER = 8;
	
	public static int getAreaCode() {
		return AREA_CODE;
	}
	
	public static int getPhoneNum() {
		return PHONE_NUM;
	}
	
	public static int getIdNumber() {
		return ID_NUMBER;
	}
}