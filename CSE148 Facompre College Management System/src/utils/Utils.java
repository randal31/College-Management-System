package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Classroom;
import model.ClassroomBag;
import model.College;
import model.CourseBag;
import model.Person;
import model.PersonBag;
import model.Textbook;
import model.TextbookBag;

public class Utils {
	public static void restore(College college) {
		restoreTextbookBag(college);
		restoreClassroomBag(college);
		restoreCourseBag(college);
		restorePersonBag(college);
	}
	
	public static void backup(College college) {
		backupTextbookBag(college);
		backupClassroomBag(college);
		backupCourseBag(college);
		backupPersonBag(college);
	}

	private static void backupPersonBag(College college) {
		try {
			FileOutputStream fos = new FileOutputStream("backup_data/personBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(college.getPersonBag());
			oos.close();
			
			FileOutputStream fosID = new FileOutputStream("backup_data/idCounter.dat");
			DataOutputStream dos = new DataOutputStream(fosID);
			dos.writeInt(Person.getIdCounter());
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void backupClassroomBag(College college) {
		try {
			FileOutputStream fos = new FileOutputStream("backup_data/classroomBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(college.getClassroomBag());
			oos.close();
			
			FileOutputStream fosID = new FileOutputStream("backup_data/roomCounter.dat");
			DataOutputStream dos = new DataOutputStream(fosID);
			dos.writeInt(Classroom.getRoomCounter());
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void backupTextbookBag(College college) {
		try {
			FileOutputStream fos = new FileOutputStream("backup_data/textbookBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(college.getTextbookBag());
			oos.close();
			
			FileOutputStream fosISBN = new FileOutputStream("backup_data/isbnCounter.dat");
			DataOutputStream dos = new DataOutputStream(fosISBN);
			dos.writeInt(Textbook.getIsbnCounter());
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static void backupCourseBag(College college) {
		try {
			FileOutputStream fos = new FileOutputStream("backup_data/courseBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(college.getCourseBag());
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void restorePersonBag(College college) {
		FileInputStream fis = null;
		File file = new File("backup_data/personBag.dat");
		File fileID = new File("backup_data/idCounter.dat");
		try {
			if (file.exists()) {
				fis = new FileInputStream("backup_data/personBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setPersonBag((PersonBag) ois.readObject());
				ois.close();
			}
			if (fileID.exists()) {
				fis = new FileInputStream("backup_data/idCounter.dat");
				DataInputStream dis = new DataInputStream(fis);
				Person.setIdCounter(dis.readInt());
				dis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void restoreClassroomBag(College college) {
		FileInputStream fis = null;
		File file = new File("backup_data/classroomBag.dat");
		File fileRoomNum = new File("backup_data/roomCounter.dat");
		try {
			if (file.exists()) {
				fis = new FileInputStream("backup_data/classroomBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setClassroomBag((ClassroomBag) ois.readObject());
				ois.close();
			}
			if (fileRoomNum.exists()) {
				fis = new FileInputStream("backup_data/roomCounter.dat");
				DataInputStream dis = new DataInputStream(fis);
				Classroom.setRoomCounter(dis.readInt());
				dis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void restoreTextbookBag(College college) {
		FileInputStream fis = null;
		File file = new File("backup_data/textbookBag.dat");
		File fileISBN = new File("backup_data/isbnCounter.dat");
		try {
			if (file.exists()) {
				fis = new FileInputStream("backup_data/textbookBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setTextbookBag((TextbookBag) (ois.readObject()));
				ois.close();
			}	
			if (fileISBN.exists()) {
				fis = new FileInputStream("backup_data/isbnCounter.dat");
				DataInputStream dis = new DataInputStream(fis);
				Textbook.setIsbnCounter(dis.readInt());
				dis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void restoreCourseBag(College college) {
		FileInputStream fis = null;
		File file = new File("backup_data/courseBag.dat");
		try {
			if (file.exists()) {
				fis = new FileInputStream("backup_data/courseBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setCourseBag((CourseBag) ois.readObject());
				ois.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}