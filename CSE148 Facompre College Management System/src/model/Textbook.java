package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;

import javafx.scene.control.Alert;
import utils.TextbookFactory;

public class Textbook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2700109816324652180L;
	private String bookTitle;
	private String isbn;
	private String author;
	private double price;
	
	private static int isbnCounter = 0;
	private static int bookTitleCounter = 0;
	private Random rand = new Random();
	private int randIndex = rand.nextInt(TextbookBag.getTextbookNumArray().length);
	
	public Textbook() throws FileNotFoundException {
		super();
		bookTitle = TextbookFactory.emitBookTitle(bookTitleCounter++);
		isbn = TextbookFactory.emitISBN(isbnCounter++);
		author = TextbookFactory.emitAuthor(randIndex);
		price = TextbookFactory.emitPrice();
	}
	
	public Textbook(String bookTitle, String author, String isbn, double price,
			boolean isUpdate) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		if (!isUpdate) {
			isbnCounter++;
		}
	}

	public void write(Textbook textbook, Alert errorMsg, boolean newFile) {
		File fw = new File("output_data/textbooks.txt");
		try {
			if (newFile) {
				PrintWriter textbookRefresh = new PrintWriter(fw);
				textbookRefresh.close();
			}
			PrintWriter textbookWriter = new PrintWriter(new FileWriter(fw, true));
			textbookWriter.write(textbook.toString() + "\n");
			textbookWriter.close();
		} catch (IOException e) {
			errorMsg.setTitle("IOException found");
			errorMsg.setHeaderText("IOException thrown on " + fw.getName());
			errorMsg.showAndWait();
		}
	}
	
	public String getBookTitle() {
		return bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public static int getIsbnCounter() {
		return isbnCounter;
	}
	
	public static void setIsbnCounter(int isbnCounter) {
		Textbook.isbnCounter = isbnCounter;
	}

	@Override
	public String toString() {
		return "Textbook [bookTitle: " + bookTitle + ", isbn: " + isbn + ",\nauthor: " + author 
				+ ", price: " + String.format("$%,.2f", price) + "]";
	}
}