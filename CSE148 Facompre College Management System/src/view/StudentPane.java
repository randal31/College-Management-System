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
import model.Grade;
import model.Major;
import model.MiniStudentCourseBag;
import model.Person;
import model.Student;
import utils.IdFactory;
import utils.MiniStudentCourseInfoBagFactory;
import utils.StudentFactory;

public class StudentPane {
	private VBox studentBox;
	private VBox inputBox;
	private VBox firstNameBox;
	private VBox lastNameBox;
	private VBox gpaBox;
	private VBox majorBox;
	private VBox idTxtBox;
	private VBox headerBox;
	private VBox buttonBox;
	private VBox idBox;
	private VBox column1;
	private VBox column2;
	
	private HBox idBtnBox;
	private HBox columnAssembly;
	private HBox listViewBox;
	
	private Label firstNameLbl;
	private Label lastNameLbl;
	private Label gpaLbl;
	private Label majorLbl;
	private Label idLbl;
	private Label headerLbl;
	private Label promptLbl;
	private Label warnLbl;
	
	private TextField firstNameFld;
	private TextField lastNameFld;
	private TextField gpaFld;
	private TextField majorFld;
	private TextField idFld;
	
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button editBtn;
	private Button updateBtn;
	private Button okBtn;
	private Button cancelBtn;
	
	private ListView<Student> studentList;
	
	private Alert errorAlrt;
	private Alert notifyAlrt;
	
	private College college;
	
	private boolean isSearch;
	private boolean isRemove;
	private boolean isEdit;
	private boolean isUpdate;
	public StudentPane(College college) {
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
		majorBox.getChildren().addAll(majorLbl, majorFld);
		majorBox.setAlignment(Pos.BASELINE_LEFT);
		column1.getChildren().addAll(firstNameBox, majorBox);
		
		lastNameBox.getChildren().addAll(lastNameLbl, lastNameFld);
		lastNameBox.setAlignment(Pos.BASELINE_LEFT);
		gpaBox.getChildren().addAll(gpaLbl, gpaFld);
		gpaBox.setAlignment(Pos.BASELINE_LEFT);
		column2.getChildren().addAll(lastNameBox, gpaBox);
		
		idTxtBox.getChildren().addAll(idLbl, idFld);
		idTxtBox.setAlignment(Pos.BASELINE_LEFT);
		idBtnBox.getChildren().addAll(okBtn, cancelBtn);
		idBtnBox.setAlignment(Pos.CENTER);
		idBox.getChildren().addAll(idTxtBox, idBtnBox);
		
		columnAssembly.getChildren().addAll(column1, column2, idBox);
		columnAssembly.setAlignment(Pos.BASELINE_CENTER);
		buttonBox.getChildren().addAll(insertBtn, searchBtn, removeBtn, editBtn, updateBtn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		listViewBox.getChildren().addAll(buttonBox, studentList);
		listViewBox.setAlignment(Pos.BOTTOM_CENTER);
		inputBox.getChildren().addAll(headerBox, columnAssembly);
		inputBox.setAlignment(Pos.CENTER);
		studentBox.getChildren().addAll(inputBox, listViewBox);
		studentBox.setAlignment(Pos.CENTER);
	}

	private void makeBoxes() {
		firstNameBox = new VBox(5);
		lastNameBox = new VBox(5);
		gpaBox = new VBox(5);
		majorBox = new VBox(5);
		
		idTxtBox = new VBox(5);
		idBox = new VBox(20);
		headerBox = new VBox(5);
		buttonBox = new VBox(20);
		idBtnBox = new HBox(20);
		
		column1 = new VBox(10);
		column2 = new VBox(10);
		columnAssembly = new HBox(20);
		
		listViewBox = new HBox(20);
		inputBox = new VBox(30);
		studentBox = new VBox(50);
	}
	
	private void makeListView() {
		studentList = new ListView<Student>();
		studentList.setPrefSize(600, 250);
		Person person;
		int nStudent = 0;
		for (int i = 0; i < college.getPersonBag().getnElems(); i++) {
			person = college.getPersonBag().getPersonArray()[i];
			if (person instanceof Student) {
				person.write(person, errorAlrt, (nStudent++ == 0) ? true : false);
				studentList.getItems().add((Student) person);
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
		gpaLbl = new Label("Gpa");
		majorLbl = new Label("Major");
		idLbl = new Label("ID Number");
		headerLbl = new Label("Student");
		promptLbl = new Label("");
		warnLbl = new Label("");
	}
	
	private void makeTxtFlds() {
		firstNameFld = new TextField();
		firstNameFld.setPromptText("first name");
		lastNameFld = new TextField();
		lastNameFld.setPromptText("last name");
		gpaFld = new TextField();
		availableOff(gpaFld);
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
			idPrompt("update");
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
		Student student = null;
		if (isSearch || isEdit) {
			if (isSearch) {
				action1 = "search";
				action2 = "found";
			}
			if (isInput) {
				try {
					student = (Student) college.getPersonBag().findByID(idFld.getText());
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
					student = (Student) college.getPersonBag().findByID(idFld.getText());
				} catch (ClassCastException e) {
					classCastException();
					wrongClass = true;
				} finally {
					if (!wrongClass) {
						student = (Student) college.getPersonBag().deleteByID(idFld.getText());
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
					notifyAlrt.setTitle("Student " + action1 + " successful!");
					notifyAlrt.setHeaderText(student + "\nhas been " + action2);
					notifyAlrt.showAndWait();	
				}
				exitIdPrompt();
			}
			else {
				if (isInput) {
					availableOn(majorFld);
					majorFld.setText(student.getMajor().toString());
					availableOn(gpaFld);
					gpaFld.setText(String.valueOf(student.getGpa()));
					
					promptLbl.setText("Edit chosen student");
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
			
			if (!isEdit) {
				if (!idFld.getText().isEmpty()) {
					idFld.clear();
				}
				promptLbl.setText("");
			}
			else {
				if (isInput) {
					firstNameFld.setText(student.getFirstName());
					lastNameFld.setText(student.getLastName());
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
		errorAlrt.setHeaderText("input cannot be existing faculty id");
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
		if (!isEmpty()) {
			if (isUpdate) {
				if (checkMajor() && checkGpa()) {
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
			id = IdFactory.emitID(String.valueOf(Student.getIdCounter()), "0");
		}
		else {
			id = idFld.getText();
		}
		
		double gpa;
		Major major;
		Grade letterGrade;
		MiniStudentCourseBag miniStudentCourseBag;
		int index = 0;
		if (isUpdate) {
			while (!id.equals(studentList.getItems().get(index).getId())) {
				index++;
			}
			gpa = Double.parseDouble(gpaFld.getText().replaceAll("\\s+", ""));
			major = Major.valueOf(majorFld.getText().replaceAll("\\s+", ""));
			miniStudentCourseBag = studentList.getItems().get(index).getMiniStudentCourseBag();
			output(firstName, lastName, id, gpa, major, miniStudentCourseBag, "update",
					"updated", true);
		}
		else {
			gpa = StudentFactory.emitGpa();
			letterGrade = MiniStudentCourseInfoBagFactory.getLetterGrade(gpa);
			major = StudentFactory.emitMajor();
			miniStudentCourseBag = StudentFactory.emitMiniStudentCourseInfoBag();
			miniStudentCourseBag.getMiniStudentInfo()[0]
					.setLetterGrade(letterGrade);
			output(firstName, lastName, id, gpa, major, miniStudentCourseBag, "insert", "added",
					false);
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
	
	private void output(String firstName, String lastName, String id, double gpa,
			Major major, MiniStudentCourseBag miniStudentCourseBag, String action1,
			String action2, boolean isUpdate) {
		Student student = new Student(firstName, lastName, id, gpa, major,
				miniStudentCourseBag, isUpdate);
		if (isUpdate) {
			college.getPersonBag().deleteByID(idFld.getText());
		}
		
		college.getPersonBag().insert(student);
		notifyAlrt.setTitle("Student " + action1 + " successful!");
		notifyAlrt.setHeaderText(student.toString() + "\nhas been " + action2);
		notifyAlrt.showAndWait();
		studentList.getItems().add(student);
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
		
		if (isUpdate) {
			if (majorFld.getText().replaceAll("\\s+", "").isEmpty()) {
				warning += "| Major is empty |";
				isEmpty = true;
			}
			
			if (gpaFld.getText().replaceAll("\\s+", "").isEmpty()) {
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
		if (idFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warnLbl.setText("| ID is empty |");
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

	private boolean checkMajor() {
		for (int i = 0; i < Major.values().length; i++) {
			if (majorFld.getText().equals(Major.values()[i].toString())) {
				return true;
			}
		}
		warnLbl.setText("| Major not on list |");
		return false;
	}
	
	private boolean checkGpa() {
		if (Double.parseDouble(gpaFld.getText()) < 0) {
			warnLbl.setText("| gpa cannot be negative |");
			return false;
		}
		
		if (Double.parseDouble(gpaFld.getText()) > 4) {
			warnLbl.setText("| gpa cannot be greater than 4.0 |");
		}
		return true;
	}
	
	public VBox getStudentBox() {
		return studentBox;
	}
}