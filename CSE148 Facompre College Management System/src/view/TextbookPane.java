package view;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.College;
import model.Textbook;
import utils.TextbookFactory;

public class TextbookPane {
	private VBox textbookBox;
	private VBox inputBox;
	private VBox titleBox;
	private VBox authorBox;
	private VBox priceBox;
	private VBox isbnTxtBox;
	private VBox headerBox;
	private VBox buttonBox;
	private VBox isbnBox;
	private VBox column1;
	private VBox column2;
	
	private HBox isbnBtnBox;
	private HBox columnAssembly;
	private HBox listViewBox;
	
	private Label titleLbl;
	private Label authorLbl;
	private Label priceLbl;
	private Label isbnLbl;
	private Label headerLbl;
	private Label promptLbl;
	private Label warnLbl;
	
	private TextField titleFld;
	private TextField authorFld;
	private TextField priceFld;
	private TextField isbnFld;
	
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button editBtn;
	private Button updateBtn;
	private Button okBtn;
	private Button cancelBtn;
	
	private ListView<Textbook> textbookList;
	
	private Alert errorAlrt;
	private Alert notifyAlrt;
	
	private College college;
	
	private boolean isSearch;
	private boolean isRemove;
	private boolean isEdit;
	private boolean isUpdate;
	public TextbookPane(College college) {
		isSearch = false;
		isRemove = false;
		isEdit = false;
		isUpdate = false;
		this.college = college;
		makeAlerts();
		makeBtns();
		makeLbls();
		makeTxtFlds();
		makeBoxes();
		makeListView();
		assembleChildren();
		setBtnListener();
	}
	
	private void assembleChildren() {
		headerBox.getChildren().addAll(headerLbl, promptLbl, warnLbl);
		headerBox.setAlignment(Pos.BASELINE_CENTER);
		titleBox.getChildren().addAll(titleLbl, titleFld);
		titleBox.setAlignment(Pos.BASELINE_LEFT);
		priceBox.getChildren().addAll(priceLbl, priceFld);
		priceBox.setAlignment(Pos.BASELINE_LEFT);
		column1.getChildren().addAll(titleBox, priceBox);
		
		authorBox.getChildren().addAll(authorLbl, authorFld);
		authorBox.setAlignment(Pos.BASELINE_LEFT);
		isbnTxtBox.getChildren().addAll(isbnLbl, isbnFld);
		isbnTxtBox.setAlignment(Pos.BASELINE_LEFT);
		isbnBtnBox.getChildren().addAll(okBtn, cancelBtn);
		isbnBtnBox.setAlignment(Pos.CENTER);
		isbnBox.getChildren().addAll(isbnTxtBox, isbnBtnBox);
		column2.getChildren().addAll(authorBox, isbnBox);
		
		columnAssembly.getChildren().addAll(column1, column2);
		columnAssembly.setAlignment(Pos.BASELINE_CENTER);
		buttonBox.getChildren().addAll(insertBtn, searchBtn, removeBtn, editBtn, updateBtn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		listViewBox.getChildren().addAll(buttonBox, textbookList);
		listViewBox.setAlignment(Pos.BOTTOM_CENTER);
		inputBox.getChildren().addAll(headerBox, columnAssembly);
		inputBox.setAlignment(Pos.CENTER);
		textbookBox.getChildren().addAll(inputBox, listViewBox);
		textbookBox.setAlignment(Pos.CENTER);
	}

	private void makeBoxes() {
		titleBox = new VBox(5);
		authorBox = new VBox(5);
		priceBox = new VBox(5);
		isbnTxtBox = new VBox(5);
		isbnBox = new VBox(20);
		headerBox = new VBox(5);
		buttonBox = new VBox(20);
		isbnBtnBox = new HBox(20);
		column1 = new VBox(10);
		column2 = new VBox(10);
		columnAssembly = new HBox(20);
		listViewBox = new HBox(20);
		inputBox = new VBox(30);
		textbookBox = new VBox(50);
	}
	
	private void makeListView() {
		textbookList = new ListView<Textbook>();
		textbookList.setPrefSize(600, 250);
		int nTextbook = 0;
		Textbook textbook;
		for (int i = 0; i < college.getTextbookBag().getnElems(); i++) {
			textbook = college.getTextbookBag().getTextbookArray()[i];
			textbook.write(textbook, errorAlrt, (nTextbook++ == 0) ? true : false);
			textbookList.getItems().add(textbook);
		}
	}
	
	private void makeAlerts() {
		errorAlrt = new Alert(AlertType.ERROR);
		notifyAlrt = new Alert(AlertType.INFORMATION);
	}
	
	private void makeLbls() {
		titleLbl = new Label("Book Title");
		authorLbl = new Label("Book Author");
		priceLbl = new Label("Price");
		isbnLbl = new Label("ISBN");
		headerLbl = new Label("Textbook");
		promptLbl = new Label("");
		warnLbl = new Label("");
	}
	
	private void makeTxtFlds() {
		titleFld = new TextField();
		titleFld.setPromptText("first name");
		authorFld = new TextField();
		authorFld.setPromptText("last name");
		priceFld = new TextField();
		availableOff(priceFld);
		isbnFld = new TextField();
		availableOff(isbnFld);
	}

	private void makeBtns() {
		insertBtn = new Button("INSERT");
		insertBtn.setPrefSize(90, 25);
		searchBtn = new Button("SEARCH");
		searchBtn.setPrefSize(90, 25);
		removeBtn = new Button("REMOVE");
		removeBtn.setPrefSize(90, 25);
		editBtn = new Button("EDIT");
		editBtn.setPrefSize(90, 25);
		updateBtn = new Button("UPDATE");
		updateBtn.setPrefSize(90, 25);
		okBtn = new Button("OK");
		cancelBtn = new Button("CANCEL");
		
		if (college.getTextbookBag().getnElems() == 0) {
			searchBtn.setDisable(true);
			removeBtn.setDisable(true);
			editBtn.setDisable(true);
		}
		updateBtn.setDisable(true);
		okBtn.setDisable(true);
		cancelBtn.setDisable(true);
	}
	
	private void setBtnListener() {
		insertBtn.setOnAction(e -> {
			txtFldPrompt();
		});
		
		searchBtn.setOnAction(e -> {
			isSearch = true;
			isbnPrompt("search");
		});
		
		removeBtn.setOnAction(e -> {
			isRemove = true;
			isbnPrompt("remove");
		});
		
		editBtn.setOnAction(e -> {
			isEdit = true;
			isbnPrompt("edit");
		});
		
		updateBtn.setOnAction(e -> {
			txtFldPrompt();
		});
		
		okBtn.setOnAction(e -> {
			if (!emptyISBN() && !invalidISBN()) {
				isbnPromptEnd(true);
			}
		});
		
		cancelBtn.setOnAction(e -> {
			isbnPromptEnd(false);
		});
	}
	
	private boolean isEmpty() {
		String warning = "";
		boolean isEmpty = false;
		if (titleFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| First name is empty |";
			isEmpty = true;
		}
		
		if (authorFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| Last name is empty |";
			isEmpty = true;
		}
		
		if (isUpdate) {
			if (priceFld.getText().replaceAll("\\s+", "").isEmpty()) {
				warning += "| Price is empty |";
				isEmpty = true;
			}
		}
		
		warnLbl.setText(warning);
		if (isEmpty) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean emptyISBN() {
		if (isbnFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warnLbl.setText("| ISBN is empty |");
			return true;
		}
		return false;
	}
	
	private boolean invalidISBN() {
		if (college.getTextbookBag().findByISBN(isbnFld.getText()) == null) {
			warnLbl.setText("| ISBN " +  isbnFld.getText() + " not found |");
			return true;
		}
		return false;
	}
	
	private boolean checkPrice() {
		if (Double.parseDouble(priceFld.getText()) < 0) {
			warnLbl.setText("| price cannot be negative |");
			return false;
		}
		return true;
	}
	
	private void isbnAvailable() {
		isbnFld.clear();
		isbnFld.setEditable(true);
		okBtn.setDisable(false);
		cancelBtn.setDisable(false);
	}
	
	private void isbnPrompt(String actionStr) {
		availableOff(titleFld);
		availableOff(authorFld);
		isbnAvailable();
		promptLbl.setText("Enter in ISBN to " + actionStr + ":");
		insertBtn.setDisable(true);
		searchBtn.setDisable(true);
		removeBtn.setDisable(true);
		if (!editBtn.isDisable()) {
			editBtn.setDisable(true);
		}
		
		if (!warnLbl.getText().isEmpty()) {
			warnLbl.setText("");
		}
	}
	
	private void isbnPromptEnd(boolean isInput) {
		String action1 = "";
		String action2 = "";
		Textbook textbook = null;
		if (isSearch || isEdit) {
			if (isSearch) {
				isSearch = false;
				action1 = "search";
				action2 = "found";
			}
			if (isInput) {
				textbook = college.getTextbookBag().findByISBN(isbnFld.getText());
			}
		}
		
		if (isRemove) {
			isRemove = false;
			action1 = "removal";
			action2 = "removed";
			if (isInput) {
				textbook = college.getTextbookBag().deleteByISBN(isbnFld.getText());
				if (college.getTextbookBag().getnElems() == 0) {
					searchBtn.setDisable(true);
					removeBtn.setDisable(true);
					editBtn.setDisable(true);
				}
			}
		}
		
		if (!isEdit) {
			if (isInput) {
				notifyAlrt.setTitle("Textbook " + action1 + " successful!");
				notifyAlrt.setHeaderText(textbook + "\nhas been " + action2);
				notifyAlrt.showAndWait();	
			}
			exitIsbnPrompt();
		}
		else {
			if (isInput) {
				availableOn(priceFld);
				priceFld.setText(String.valueOf(textbook.getPrice()));
				
				promptLbl.setText("Edit chosen textbook");
				if (!warnLbl.getText().isEmpty()) {
					warnLbl.setText("");
				}
				isUpdate = true;
				updateBtn.setDisable(false);
			}
			else {
				exitIsbnPrompt();
			}
		}
		
		availableOn(titleFld);
		availableOn(authorFld);
		
		if (!isEdit) {
			if (!isbnFld.getText().isEmpty()) {
				isbnFld.clear();
			}
			promptLbl.setText("");
		}
		else {
			if (isInput) {
				titleFld.setText(textbook.getBookTitle());
				authorFld.setText(textbook.getAuthor());
			}
			isEdit = false;
		}
		
		isbnFld.setEditable(false);
		okBtn.setDisable(true);
		cancelBtn.setDisable(true);
	}
	
	private void exitIsbnPrompt() {
		insertBtn.setDisable(false);
		searchBtn.setDisable(false);
		removeBtn.setDisable(false);
		editBtn.setDisable(false);
	}
	
	private void availableOff(TextField txtFld) {
		txtFld.setText("N/A");
		txtFld.setEditable(false);
	}
	
	private void availableOn(TextField txtFld) {
		txtFld.setEditable(true);
		txtFld.clear();
	}
	
	private void txtFldPrompt() {
		if (!isEmpty()) {
			if (isUpdate) {
				if (checkPrice()) {
					txtFldPromptEnd();
				}
			}
			else {
				txtFldPromptEnd();
			}
		}
	}
	
	private void txtFldPromptEnd() {
		if (!warnLbl.getText().isEmpty()) {
			warnLbl.setText("");
		}
		String bookTitle = titleFld.getText().replaceAll("\\s+", "");
		String bookAuthor = authorFld.getText().replaceAll("\\s+", "");
		String isbn;
		if (!isUpdate) {
			isbn = TextbookFactory.emitISBN(Textbook.getIsbnCounter());
		}
		else {
			isbn = isbnFld.getText();
		}
		
		double price;
		
		if (isUpdate) {
			price = Double.parseDouble(priceFld.getText().replaceAll("\\s+", ""));
			output(bookTitle, bookAuthor, isbn, price, "update", "updated", true);
		}
		else {
			price = TextbookFactory.emitPrice();
			output(bookTitle, bookAuthor, isbn, price, "insert", "added", false);
		}
		
		if (searchBtn.isDisable() && removeBtn.isDisable() && editBtn.isDisable()) {
			if (insertBtn.isDisable()) {
				insertBtn.setDisable(false);
			}
			searchBtn.setDisable(false);
			removeBtn.setDisable(false);
			editBtn.setDisable(false);
			if (isUpdate) {
				isUpdate = false;
				updateBtn.setDisable(true);
			}
		}
	}
	
	private void output(String title, String author, String isbn, double price,
			String action1, String action2, boolean isUpdate) {
		Textbook textbook = new Textbook(title, author, isbn, price, isUpdate);
		if (isUpdate) {
			college.getTextbookBag().deleteByISBN(isbnFld.getText());
		}
		college.getTextbookBag().insert(textbook);
		notifyAlrt.setTitle("Textbook " + action1 + " successful!");
		notifyAlrt.setHeaderText(textbook.toString() + "\nhas been " + action2);
		notifyAlrt.showAndWait();
		textbookList.getItems().add(textbook);
	}
	
	public VBox getTextbookBox() {
		return textbookBox;
	}
}