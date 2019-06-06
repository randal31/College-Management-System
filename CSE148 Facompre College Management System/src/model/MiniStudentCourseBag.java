package model;

import java.io.Serializable;

public class MiniStudentCourseBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5164025779759962981L;
	private MiniStudentInfo [] miniStudentInfo;
	private int nElems;
	
	public MiniStudentCourseBag(int maxSize) {
		miniStudentInfo = new MiniStudentInfo[maxSize];
		nElems = 0;
	}

	public void insert(MiniStudentInfo courseInfo) {
		if (nElems > 0) {
			nElems++;
		}
		miniStudentInfo[nElems] = courseInfo;
	}

	public MiniStudentInfo[] getMiniStudentInfo() {
		return miniStudentInfo;
	}
	
	public int getnElems() {
		return nElems;
	}

	@Override
	public String toString() {
		return "MiniStudentCourseBag:\n[" + miniStudentInfo[nElems] + "]";
	}
}