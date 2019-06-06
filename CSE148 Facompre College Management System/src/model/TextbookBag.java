package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class TextbookBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3528982482577945557L;
	private Textbook [] textbookArray;
	private int nElems;
	
	private static String [] textbookStr;
	
	public TextbookBag(int maxSize) {
		textbookArray = new Textbook[maxSize];
		textbookStr = new String[maxSize];
		nElems = 0;
	}
	
	public void insert(Textbook textbook) {
		textbookArray[nElems++] = textbook;
	}
	
	public void display() throws IOException {
		System.out.println("\n-textbook bag-");
		FileWriter fw = new FileWriter("output_data/textbooks.txt");
		PrintWriter textbookWriter = new PrintWriter(fw);
		for (int i = 0; i < nElems; i++) {
			textbookWriter.println(textbookArray[i]);
			System.out.println(textbookArray[i]);
		}
		System.out.println();
		textbookWriter.close();
	}
	
	public Textbook findByISBN(String isbn) {
		for (int i = 0; i < nElems; i++) {
			if (textbookArray[i].getIsbn().equals(isbn)) {
				return textbookArray[i];
			}
		}
		return null;
	}
	
	public Textbook deleteByISBN(String id) {
		int i = 0;
		for(i = 0; i < nElems; i++) {
			if(textbookArray[i].getIsbn().equals(id)) {
				break;
			}
		}
		if( i == nElems ) {
			return null;
		} else {
			Textbook temp = textbookArray[i];
			for(int j = i; j < nElems-1; j++) {
				textbookArray[j] = textbookArray[j+1]; 
			}
			nElems--;
			return temp;
		}
	}
	
	public int getnElems() {
		return nElems;
	}
	
	public Textbook[] getTextbookArray() {
		return textbookArray;
	}

	public static String[] getTextbookNumArray() {
		return textbookStr;
	}
}