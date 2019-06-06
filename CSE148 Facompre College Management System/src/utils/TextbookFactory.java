package utils;

import java.io.File;
import java.util.Random;

import model.Person;

public class TextbookFactory {
	
	public static String emitBookTitle(int lineCounter) {
		File txtfile = new File("input_data/textbook_titles.txt");
		String bookTitle = NameFactory.emitText(txtfile, lineCounter);
		return bookTitle;
	}
	
	public static String emitISBN(int lineCounter) {
		File txtfile = new File("input_data/textbook_isbns.txt");
		String isbn = NameFactory.emitText(txtfile, lineCounter);
		return isbn;
	}
	
	public static String emitAuthor(int nameIndex) {
		String firstName = "", lastName = "";
		firstName = NameFactory.emitFirstName(Person.getFirstNameCounter(), false);
		lastName = NameFactory.emitLastName(Person.getLastNameCounter(), false);
		String authorName = firstName + " " + lastName;
		return authorName;
	}
	
	public static double emitPrice() {
		Random rand = new Random();
		double price = rand.nextInt(30);
		return price;
	}	
}