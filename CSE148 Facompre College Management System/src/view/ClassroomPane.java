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
import model.Building;
import model.Classroom;
import model.College;
import utils.ClassroomFactory;

public class ClassroomPane {
	private VBox classroomBox;
	private VBox inputBox;
	private VBox bldgBox;
	private VBox roomTxtBox;
	
	private VBox headerBox;
	private VBox buttonBox;
	private VBox roomNumBox;
	
	private HBox roomBtnBox;
	private HBox listViewBox;
	private HBox fldRow;
	
	private Label bldgLbl;
	private Label roomLbl;
	private Label headerLbl;
	private Label promptLbl;
	private Label warnLbl;
	
	private TextField bldgFld;
	private TextField roomFld;
	
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button editBtn;
	private Button updateBtn;
	private Button okBtn;
	private Button cancelBtn;
	
	private ListView<Classroom> classroomList;
	
	private Alert errorAlrt;
	private Alert notifyAlrt;
	
	private College college;
	
	private boolean isSearch;
	private boolean isRemove;
	private boolean isEdit;
	private boolean isUpdate;
	public ClassroomPane(College college) {
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
		bldgBox.getChildren().addAll(bldgLbl, bldgFld);
		bldgBox.setAlignment(Pos.BASELINE_LEFT);
		roomTxtBox.getChildren().addAll(roomLbl, roomFld);
		roomTxtBox.setAlignment(Pos.BASELINE_LEFT);
		roomBtnBox.getChildren().addAll(okBtn, cancelBtn);
		roomBtnBox.setAlignment(Pos.CENTER);
		roomNumBox.getChildren().addAll(roomTxtBox, roomBtnBox);
		fldRow.getChildren().addAll(bldgBox, roomNumBox);
		fldRow.setAlignment(Pos.CENTER);
		
		buttonBox.getChildren().addAll(insertBtn, searchBtn, removeBtn, editBtn, updateBtn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		listViewBox.getChildren().addAll(buttonBox, classroomList);
		listViewBox.setAlignment(Pos.BOTTOM_CENTER);
		inputBox.getChildren().addAll(headerBox, fldRow);
		inputBox.setAlignment(Pos.CENTER);
		classroomBox.getChildren().addAll(inputBox, listViewBox);
		classroomBox.setAlignment(Pos.CENTER);
	}

	private void makeBoxes() {
		bldgBox = new VBox(5);
		roomTxtBox = new VBox(5);
		roomNumBox = new VBox(20);
		headerBox = new VBox(5);
		buttonBox = new VBox(20);
		roomBtnBox = new HBox(20);
		fldRow = new HBox(20);
		listViewBox = new HBox(20);
		inputBox = new VBox(30);
		classroomBox = new VBox(50);
	}
	
	private void makeListView() {
		classroomList = new ListView<Classroom>();
		classroomList.setPrefSize(600, 250);
		
		int nClassroom = 0;
		Classroom classroom;
		for (int i = 0; i < college.getClassroomBag().getnElems(); i++) {
			classroom = college.getClassroomBag().getClassroomArray()[i];
			classroom.write(classroom, errorAlrt, (nClassroom++ == 0) ? true : false);
			classroomList.getItems().add(classroom);
		}
	}
	
	private void makeAlerts() {
		errorAlrt = new Alert(AlertType.ERROR);
		notifyAlrt = new Alert(AlertType.INFORMATION);
	}
	
	private void makeLbls() {
		bldgLbl = new Label("Building Name");
		roomLbl = new Label("Room Number");
		headerLbl = new Label("Classroom");
		promptLbl = new Label("");
		warnLbl = new Label("");
	}
	
	private void makeTxtFlds() {
		bldgFld = new TextField();
		bldgFld.setPromptText("bldg name");
		roomFld = new TextField();
		availableOff(roomFld);
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
		
		if (college.getClassroomBag().getnElems() == 0) {
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
			if (!emptyRoomNum() && !invalidRoomNum()) {
				roomNumPromptEnd(true);
			}
		});
		
		cancelBtn.setOnAction(e -> {
			roomNumPromptEnd(false);
		});
	}
	
	private boolean isEmpty() {
		if (bldgFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warnLbl.setText("| Building name is empty |");
			return true;
		}
		return false;
	}
	
	private boolean emptyRoomNum() {
		if (roomFld.getText().replaceAll("\\s+", "").isEmpty()) {
			warnLbl.setText("| Room number is empty |");
			return true;
		}
		return false;
	}
	
	private boolean invalidRoomNum() {
		if (college.getClassroomBag().findByRoomNum(roomFld.getText()) == null) {
			warnLbl.setText("| Room number " +  roomFld.getText() + " not found |");
			return true;
		}
		return false;
	}
	
	private boolean checkBuilding() {
		for (int i = 0; i < Building.values().length; i++) {
			if (bldgFld.getText().equals(Building.values()[i].toString())) {
				return true;
			}
		}
		warnLbl.setText("| Building not on list |");
		return false;
	}
	
	private void roomNumAvailable() {
		roomFld.clear();
		roomFld.setEditable(true);
		okBtn.setDisable(false);
		cancelBtn.setDisable(false);
	}
	
	private void isbnPrompt(String actionStr) {
		availableOff(bldgFld);
		roomNumAvailable();
		promptLbl.setText("Enter in room num to " + actionStr + ":");
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
	
	private void roomNumPromptEnd(boolean isInput) {
		String action1 = "";
		String action2 = "";
		Classroom classroom = null;
		if (isSearch || isEdit) {
			if (isSearch) {
				isSearch = false;
				action1 = "search";
				action2 = "found";
			}
			if (isInput) {
				classroom = college.getClassroomBag().findByRoomNum(roomFld.getText());
			}
		}
		
		if (isRemove) {
			isRemove = false;
			action1 = "removal";
			action2 = "removed";
			if (isInput) {
				classroom = college.getClassroomBag().deleteByRoomNum(roomFld.getText());
				if (college.getClassroomBag().getnElems() == 0) {
					searchBtn.setDisable(true);
					removeBtn.setDisable(true);
					editBtn.setDisable(true);
				}
			}
		}
		
		if (!isEdit) {
			if (isInput) {
				notifyAlrt.setTitle("Textbook " + action1 + " successful!");
				notifyAlrt.setHeaderText(classroom + "\nhas been " + action2);
				notifyAlrt.showAndWait();	
			}
			exitRoomNumPrompt();
		}
		else {
			if (isInput) {
				availableOn(roomFld);
				roomFld.setText(classroom.getRoomNumber());
				
				promptLbl.setText("Edit chosen classroom");
				if (!warnLbl.getText().isEmpty()) {
					warnLbl.setText("");
				}
				isUpdate = true;
				updateBtn.setDisable(false);
			}
			else {
				exitRoomNumPrompt();
			}
		}
		
		availableOn(bldgFld);
		
		if (!isEdit) {
			if (!roomFld.getText().isEmpty()) {
				roomFld.clear();
			}
			promptLbl.setText("");
		}
		else {
			if (isInput) {
				bldgFld.setText(classroom.getRoomBuilding().toString());
			}
			isEdit = false;
		}
		
		roomFld.setEditable(false);
		okBtn.setDisable(true);
		cancelBtn.setDisable(true);
	}
	
	private void exitRoomNumPrompt() {
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
			if (checkBuilding()) {
				txtFldPromptEnd();
			}
		}
	}
	
	private void txtFldPromptEnd() {
		if (!warnLbl.getText().isEmpty()) {
			warnLbl.setText("");
		}
		
		String roomNumber = bldgFld.getText().substring(0, 1) + 
				ClassroomFactory.emitRoomNumber(String.valueOf(Classroom.getRoomCounter()));
		
		Building buildingName = Building.valueOf(bldgFld.getText().replaceAll("\\s+", ""));
		
		if (isUpdate) {
			output(buildingName, roomNumber, "update", "updated", true);
		}
		else {
			output(buildingName, roomNumber, "insert", "added", false);
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
	
	private void output(Building building, String roomNumber, String action1, String action2,
			boolean isUpdate) {
		Classroom classroom = new Classroom(building, roomNumber, isUpdate);
		if (isUpdate) {
			college.getClassroomBag().deleteByRoomNum(roomFld.getText());
		}
		college.getClassroomBag().insert(classroom);
		notifyAlrt.setTitle("Classroom " + action1 + " successful!");
		notifyAlrt.setHeaderText(classroom.toString() + "\nhas been " + action2);
		notifyAlrt.showAndWait();
		classroomList.getItems().add(classroom);
	}
	
	public VBox getClassroomBox() {
		return classroomBox;
	}
}
