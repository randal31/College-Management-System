package view;

import config.CharLimit;
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
import model.Faculty;
import model.Major;
import model.MiniFacultyCourseBag;
import model.Person;
import model.Title;
import utils.FacultyFactory;
import utils.IdFactory;

public class FacultyPane {
	private VBox facultyBox;
	private VBox inputBox;
	private VBox firstNameBox;
	private VBox lastNameBox;
	private VBox areaCodeBox;
	private VBox phoneBox;
	private VBox fullPhoneBox;
	private VBox salaryBox;
	private VBox titleBox;
	private VBox majorBox;
	private VBox idTxtBox;
	private VBox headerBox;
	private VBox buttonBox;
	private VBox idBox;
	private VBox column1;
	private VBox column2;
	private VBox column3;
	private VBox column4;
	
	private HBox phoneRow;
	private HBox idBtnBox;
	private HBox columnAssembly;
	private HBox listViewBox;
	
	private Label firstNameLbl;
	private Label lastNameLbl;
	private Label areaCodeLbl;
	private Label phoneLbl;
	private Label fullPhoneLbl;
	private Label salaryLbl;
	private Label titleLbl;
	private Label majorLbl;
	private Label idLbl;
	private Label headerLbl;
	private Label promptLbl;
	private Label warnLbl;
	
	private TextField firstNameFld;
	private TextField lastNameFld;
	private TextField areaCodeFld;
	private TextField phoneFld;
	private TextField fullPhoneFld;
	private TextField salaryFld;
	private TextField titleFld;
	private TextField majorFld;
	private TextField idFld;
	
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button editBtn;
	private Button updateBtn;
	private Button okBtn;
	private Button cancelBtn;
	
	private ListView<Faculty> facultyList;
	
	private Alert errorAlrt;
	private Alert notifyAlrt;
	
	private College college;
	
	private boolean isSearch;
	private boolean isRemove;
	private boolean isEdit;
	private boolean isUpdate;
	public FacultyPane(College college) {
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
		firstNameBox.getChildren().addAll(firstNameLbl, firstNameFld);
		firstNameBox.setAlignment(Pos.BASELINE_LEFT);
		idTxtBox.getChildren().addAll(idLbl, idFld);
		idTxtBox.setAlignment(Pos.BASELINE_LEFT);
		idBtnBox.getChildren().addAll(okBtn, cancelBtn);
		idBtnBox.setAlignment(Pos.CENTER);
		idBox.getChildren().addAll(idTxtBox, idBtnBox);
		column1.getChildren().addAll(firstNameBox, idBox);
		
		areaCodeBox.getChildren().addAll(areaCodeLbl, areaCodeFld);
		areaCodeBox.setAlignment(Pos.BASELINE_LEFT);
		phoneBox.getChildren().addAll(phoneLbl, phoneFld);
		phoneBox.setAlignment(Pos.BASELINE_LEFT);
		phoneRow.getChildren().addAll(areaCodeBox, phoneBox);
		lastNameBox.getChildren().addAll(lastNameLbl, lastNameFld);
		lastNameBox.setAlignment(Pos.BASELINE_LEFT);
		column2.getChildren().addAll(lastNameBox, phoneRow);
		
		titleBox.getChildren().addAll(titleLbl, titleFld);
		titleBox.setAlignment(Pos.BASELINE_LEFT);
		fullPhoneBox.getChildren().addAll(fullPhoneLbl, fullPhoneFld);
		fullPhoneBox.setAlignment(Pos.BASELINE_LEFT);
		column3.getChildren().addAll(titleBox, fullPhoneBox);
		
		salaryBox.getChildren().addAll(salaryLbl, salaryFld);
		salaryBox.setAlignment(Pos.BASELINE_LEFT);
		majorBox.getChildren().addAll(majorLbl, majorFld);
		majorBox.setAlignment(Pos.BASELINE_LEFT);
		column4.getChildren().addAll(majorBox, salaryBox);
		
		buttonBox.getChildren().addAll(insertBtn, searchBtn, removeBtn, editBtn, updateBtn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		columnAssembly.getChildren().addAll(column1, column2, column3, column4);
		columnAssembly.setAlignment(Pos.BASELINE_CENTER);
		inputBox.getChildren().addAll(headerBox, columnAssembly);
		inputBox.setAlignment(Pos.CENTER);
		listViewBox.getChildren().addAll(buttonBox, facultyList);
		listViewBox.setAlignment(Pos.BOTTOM_CENTER);
		facultyBox.getChildren().addAll(inputBox, listViewBox);
		facultyBox.setAlignment(Pos.CENTER);
	}

	private void makeBoxes() {
		firstNameBox = new VBox(5);
		lastNameBox = new VBox(5);
		areaCodeBox = new VBox(5);
		phoneBox = new VBox(5);
		fullPhoneBox = new VBox(5);
		salaryBox = new VBox(5);
		titleBox = new VBox(5);
		majorBox = new VBox(5);
		idTxtBox = new VBox(5);
		
		idBox = new VBox(20);
		headerBox = new VBox(5);
		buttonBox = new VBox(20);
		
		phoneRow = new HBox(20);
		idBtnBox = new HBox(50);
		
		column1 = new VBox(10);
		column2 = new VBox(10);
		column3= new VBox(10);
		column4 = new VBox(10);
		
		columnAssembly = new HBox(20);
		listViewBox = new HBox(20);
		
		inputBox = new VBox(30);
		facultyBox = new VBox(50);
	}
	
	private void makeListView() {
		facultyList = new ListView<Faculty>();
		facultyList.setPrefSize(600, 250);
		
		Person person;
		int nFaculty = 0;
		for (int i = 0; i < college.getPersonBag().getnElems(); i++) {
			person = college.getPersonBag().getPersonArray()[i];
			if (person instanceof Faculty) {
				person.write(person, errorAlrt, (nFaculty++ == 0) ? true : false);
				facultyList.getItems().add((Faculty) person);
			}
		}
	}
	
	private void makeAlerts() {
		errorAlrt = new Alert(AlertType.ERROR);
		notifyAlrt = new Alert(AlertType.INFORMATION);
	}
	
	private void makeLbls() {
		firstNameLbl = new Label("First Name");
		lastNameLbl = new Label("Last Name");
		areaCodeLbl = new Label("Area Code");
		phoneLbl = new Label("Phone Num");
		fullPhoneLbl = new Label("(Full) Phone Number");
		salaryLbl = new Label("Salary");
		titleLbl = new Label("Title");
		majorLbl = new Label("Major");
		idLbl = new Label("ID Number");
		headerLbl = new Label("Faculty");
		promptLbl = new Label("");
		warnLbl = new Label("");
	}
	
	private void makeTxtFlds() {
		firstNameFld = new TextField();
		firstNameFld.setPromptText("first name");
		lastNameFld = new TextField();
		lastNameFld.setPromptText("last name");
		areaCodeFld = new TextField();
		areaCodeFld.setPromptText("( # # # )");
		areaCodeFld.setMaxWidth(70);
		phoneFld = new TextField();
		phoneFld.setPromptText("phone number");
		
		fullPhoneFld = new TextField();
		availableOff(fullPhoneFld);
		salaryFld = new TextField();
		availableOff(salaryFld);
		titleFld = new TextField();
		availableOff(titleFld);
		majorFld = new TextField();
		availableOff(majorFld);
		
		idFld = new TextField();
		idFld.setEditable(false);
		idFld.setPromptText(IdFactory.emitID(idFld.getText(), "?"));
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
		
		if (college.getPersonBag().getnElems() == 0) {
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
			idPrompt("search");
		});
		
		removeBtn.setOnAction(e -> {
			isRemove = true;
			idPrompt("remove");
		});
		
		editBtn.setOnAction(e -> {
			isEdit = true;
			idPrompt("edit");
		});
		
		updateBtn.setOnAction(e -> {
			txtFldPrompt();
		});
		
		okBtn.setOnAction(e -> {
			if (!emptyID() && !invalidID()) {
				idPromptEnd(true);
			}
		});
		
		cancelBtn.setOnAction(e -> {
			idPromptEnd(false);
		});
	}
	
	
	
	private void idAvailable() {
		idFld.setEditable(true);
		okBtn.setDisable(false);
		cancelBtn.setDisable(false);
	}
	
	private void idPrompt(String actionStr) {
		availableOff(firstNameFld);
		availableOff(lastNameFld);
		availableOff(areaCodeFld);
		availableOff(phoneFld);
		idAvailable();
		promptLbl.setText("Enter in ID number to " + actionStr + ":");
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
	
	private void idPromptEnd(boolean isInput) {
		String action1 = "";
		String action2 = "";
		boolean wrongClass = false;
		Faculty faculty = null;
		if (isSearch || isEdit) {
			if (isSearch) {
				action1 = "search";
				action2 = "found";
			}
			if (isInput) {
				try {
					faculty = (Faculty) college.getPersonBag().findByID(idFld.getText());
				} catch (ClassCastException e) {
					classCastException();
					wrongClass = true;
				}
			}
		}
		
		if (isRemove) {
			action1 = "removal";
			action2 = "removed";
			if (isInput) {
				try {
					faculty = (Faculty) college.getPersonBag().findByID(idFld.getText());
				} catch (ClassCastException e) {
					classCastException();
					wrongClass = true;
				} finally {
					if (!wrongClass) {
						faculty = (Faculty) college.getPersonBag().deleteByID(idFld.getText());
					}	
				}
				
				if (college.getPersonBag().getnElems() == 0) {
					searchBtn.setDisable(true);
					removeBtn.setDisable(true);
					editBtn.setDisable(true);
				}
			}
		}
		
		if (!wrongClass) {
			isSearch = false;
			isRemove = false;
			if (!isEdit) {
				if (isInput) {
					notifyAlrt.setTitle("Faculty " + action1 + " successful!");
					notifyAlrt.setHeaderText(faculty + "\nhas been " + action2);
					notifyAlrt.showAndWait();	
				}
				exitIdPrompt();
			}
			else {
				if (isInput) {
					fullPhoneFld.setText(faculty.getOfficePhone());
					availableOn(titleFld);
					titleFld.setText(faculty.getTitle().toString());
					availableOn(majorFld);
					majorFld.setText(faculty.getMajor().toString());
					availableOn(salaryFld);
					salaryFld.setText(String.valueOf(faculty.getSalary()));
					promptLbl.setText("Edit chosen faculty");
					if (!warnLbl.getText().isEmpty()) {
						warnLbl.setText("");
					}
					isUpdate = true;
					updateBtn.setDisable(false);
				}
				else {
					exitIdPrompt();
				}
			}
			
			availableOn(firstNameFld);
			availableOn(lastNameFld);
			availableOn(areaCodeFld);
			availableOn(phoneFld);
			
			if (!isEdit) {
				if (!idFld.getText().isEmpty()) {
					idFld.clear();
				}
				promptLbl.setText("");
			}
			else {
				if (isInput) {
					firstNameFld.setText(faculty.getFirstName());
					lastNameFld.setText(faculty.getLastName());
				}
				isEdit = false;
			}
			idFld.setEditable(false);
			okBtn.setDisable(true);
			cancelBtn.setDisable(true);
		}
	}
	
	private void exitIdPrompt() {
		insertBtn.setDisable(false);
		searchBtn.setDisable(false);
		removeBtn.setDisable(false);
		editBtn.setDisable(false);
	}
	
	private void classCastException() {
		errorAlrt.setTitle("ClassCastException Found");
		errorAlrt.setHeaderText("input cannot be existing student id");
		errorAlrt.showAndWait();
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
		if (!charLimit() && !isEmpty()) {
			if (isUpdate) {
				if (checkTitle() && checkMajor() && checkSalary()) {
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
		String firstName = firstNameFld.getText().replaceAll("\\s+", "");
		String lastName = lastNameFld.getText().replaceAll("\\s+", "");
		String id;
		if (!isUpdate) {
			id = IdFactory.emitID(String.valueOf(Faculty.getIdCounter()), "0");
		}
		else {
			id = idFld.getText();
		}
		
		String fullPhoneNum = FacultyFactory.emitPhoneNumber(phoneFld.getText()
				.replaceAll("\\s+", ""), areaCodeFld.getText().replaceAll("\\s+", ""));
		double salary;
		Title title;
		Major major;
		MiniFacultyCourseBag miniFacultyCourseBag;
		int index = 0;
		
		if (isUpdate) {
			while (!id.equals(facultyList.getItems().get(index).getId())) {
				index++;
			}
			salary = Double.parseDouble(salaryFld.getText().replaceAll("\\s+", ""));
			title = Title.valueOf(titleFld.getText().replaceAll("\\s+", ""));
			major = Major.valueOf(majorFld.getText().replaceAll("\\s+", ""));
			miniFacultyCourseBag = facultyList.getItems().get(index)
					.getMiniFacultyCourseBag();
			output(firstName, lastName, id, fullPhoneNum, salary,
					title, major, miniFacultyCourseBag, "update", "updated", true);
		}
		else {
			salary = FacultyFactory.emitSalary();
			title = FacultyFactory.emitTitle();
			major = FacultyFactory.emitMajor();
			miniFacultyCourseBag = FacultyFactory.emitMiniFacultyCourseInfoBag();
			output(firstName, lastName, id, fullPhoneNum, salary,
					title, major, miniFacultyCourseBag, "insert", "added", false);
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
	
	private void output(String firstName, String lastName, String id, String fullPhoneNum,
			double salary, Title title, Major major, MiniFacultyCourseBag miniFacultyCourseBag,
			String action1, String action2, boolean isUpdate) {
		Faculty faculty = new Faculty(firstName, lastName, id,
				fullPhoneNum, salary, title, major, miniFacultyCourseBag, isUpdate);
		if (isUpdate) {
			college.getPersonBag().deleteByID(idFld.getText());
		}
		college.getPersonBag().insert(faculty);
		notifyAlrt.setTitle("Faculty " + action1 + " successful!");
		notifyAlrt.setHeaderText(faculty.toString() + "\nhas been " + action2);
		notifyAlrt.showAndWait();
		facultyList.getItems().add(faculty);
	}
	
	private boolean charLimit() {
		String warning = "";
		boolean isAtLimit = false;
		if (areaCodeFld.getText().replaceAll("\\s+", "").length() != CharLimit.getAreaCode()) {
			warning += "| Area code must have " + CharLimit.getAreaCode() + " characters |";
			isAtLimit = true;
		}
		
		if (phoneFld.getText().replaceAll("\\s+", "").length() != CharLimit.getPhoneNum()) {
			warning += "| Phone number must have " + CharLimit.getPhoneNum() + " characters |";
			isAtLimit = true;
		}
		warnLbl.setText(warning);
		if (isAtLimit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isEmpty() {
		String warning = "";
		boolean isEmpty = false;
		if (firstNameFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| First name is empty |";
			isEmpty = true;
		}
		
		if (lastNameFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| Last name is empty |";
			isEmpty = true;
		}
		
		if (areaCodeFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| Area code is empty |";
			isEmpty = true;
		}
		
		if (phoneFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| Phone number is empty |";
			isEmpty = true;
		}
		
		if (isUpdate) {
			if (titleFld.getText().replaceAll("\\s+", "").isEmpty()) {
				warning += "| Title is empty |";
				isEmpty = true;
			}
			
			if (majorFld.getText().replaceAll("\\s+", "").isEmpty()) {
				warning += "| Major is empty |";
				isEmpty = true;
			}
			
			if (salaryFld.getText().replaceAll("\\s+", "").isEmpty()) {
				warning += "| Salary is empty |";
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
	
	private boolean emptyID() {
		String warning = "";
		boolean isEmpty = false;
		if (idFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warning += "| ID number is empty |";
			isEmpty = true;
		}
		
		warnLbl.setText(warning);
		if (isEmpty) {
			return true;
		}
		return false;
	}
	
	private boolean invalidID() {
		String warning = "";
		boolean isInvalid = false;
		idFld.setText(IdFactory.emitID(idFld.getText().replaceAll("\\s+", ""), "0"));
		if (idFld.getText().length() > CharLimit.getIdNumber()) {
			warning += "| ID number cannot exceed " + CharLimit.getIdNumber() + " numbers |";
			isInvalid = true;
		}
		if (college.getPersonBag().findByID(idFld.getText()) == null) {
			warning += "| ID number " +  idFld.getText() + " not found |";
			isInvalid = true;
		}
		
		warnLbl.setText(warning);
		if (isInvalid) {
			return true;
		}
		return false;
	}

	private boolean checkTitle() {
		for (int i = 0; i < Title.values().length; i++) {
			if (titleFld.getText().equals(Title.values()[i].toString())) {
				
				return true;
			}
		}
		warnLbl.setText("| Title not on list |");
		return false;
	}
	
	private boolean checkMajor() {
		for (int i = 0; i < Major.values().length; i++) {
			if (majorFld.getText().equals(Major.values()[i].toString())) {
				return true;
			}
		}
		warnLbl.setText("| Major not on list |");
		return false;
	}
	
	private boolean checkSalary() {
		if (Double.parseDouble(salaryFld.getText()) < 0) {
			warnLbl.setText("| Salary cannot be negative |");
			return false;
		}
		return true;
	}
	
	public VBox getFacultyBox() {
		return facultyBox;
	}
}