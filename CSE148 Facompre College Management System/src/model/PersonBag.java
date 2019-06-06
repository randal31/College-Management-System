package model;

import java.io.Serializable;

public class PersonBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2100880896796520712L;
	private Person [] personArray;
	private int nElems;
	
	public PersonBag(int maxSize) {
		personArray = new Person[maxSize];
		nElems = 0;
	}

	public void insert(Person person) {
		personArray[nElems++] = person;
	}
	
	public Person findByID(String id) {
		for (int i = 0; i < nElems; i++) {
			if (personArray[i].getId().equals(id)) {
				return personArray[i];
			}
		}
		return null;
	}
	
	public Person deleteByID(String id) {
		int i = 0;
		for(i = 0; i < nElems; i++) {
			if(personArray[i].getId().equals(id)) {
				break;
			}
		}
		if( i == nElems ) {
			return null;
		} else {
			Person temp = personArray[i];
			for(int j = i; j < nElems-1; j++) {
				personArray[j] = personArray[j+1]; 
			}
			nElems--;
			return temp;
		}
	}

	public Person[] getPersonArray() {
		return personArray;
	}
	
	public void setnElems() {
		for (int i = 0; i < personArray.length; i++) {
			nElems++;
		}
	}

	public int getnElems() {
		return nElems;
	}
}