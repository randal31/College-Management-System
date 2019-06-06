package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javafx.scene.control.Alert;
import utils.IdFactory;
import utils.NameFactory;

public abstract class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1966320869713040026L;
	private String firstName;
	private String lastName;
	private String id;
	
	private static int idCounter = 1;
	private static int firstNameCounter = 0;
	private static int lastNameCounter = 0;
	
	public Person() {
		firstName = NameFactory.emitFirstName(firstNameCounter++, true);
		lastName = NameFactory.emitLastName(lastNameCounter++, true);
		id = IdFactory.emitID(String.valueOf(idCounter++), "0");
	}
	
	public Person(String firstName, String lastName, String id, boolean isUpdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		if (!isUpdate) {
			idCounter++;
		}
	}
	
	public void write(Person person, Alert errorMsg, boolean newFile) {
		File fw = null;
		try {
			if (person instanceof Student) {
				fw = new File("output_data/students.txt");
			}
			else {
				fw = new File("output_data/faculty.txt");
			}
			if (newFile) {
				PrintWriter personRefresh = new PrintWriter(fw);
				personRefresh.close();
			}
			PrintWriter personWriter = new PrintWriter(new FileWriter(fw, true));
			personWriter.write(person.toString() + "\n");
			//personWriter.append(person.toString() + "\n");
			personWriter.close();
		} catch (IOException e) {
			errorMsg.setTitle("IOException found");
			errorMsg.setHeaderText("IOException thrown on " + fw.getName());
			errorMsg.showAndWait();
		}
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getId() {
		return id;
	}

	public static void setIdCounter(int idCounter) {
		Person.idCounter = idCounter;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static int getFirstNameCounter() {
		return firstNameCounter++;
	}

	public static int getLastNameCounter() {
		return lastNameCounter++;
	}

	@Override
	public String toString() {
		return "Person [first name: " + firstName + ", last name: " + lastName 
				+ ", id: " + id + "]";
	}
}