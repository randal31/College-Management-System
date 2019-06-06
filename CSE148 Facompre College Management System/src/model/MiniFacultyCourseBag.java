package model;

import java.io.Serializable;

public class MiniFacultyCourseBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2470598304412176668L;
	private MiniFacultyInfo [] miniFacultyInfo;
	private int nElems;
	
	public MiniFacultyCourseBag(int maxSize) {
		miniFacultyInfo = new MiniFacultyInfo[maxSize];
		nElems = 0;
	}

	public void insert(MiniFacultyInfo faculty) {
		if (nElems > 0) {
			nElems++;
		}
		miniFacultyInfo[nElems] = faculty;
	}

	public MiniFacultyInfo[] getMiniFacultyInfo() {
		return miniFacultyInfo;
	}

	@Override
	public String toString() {
		return "MiniFacultyCourseBag:\n[" + miniFacultyInfo[nElems] + "]";
	}
}