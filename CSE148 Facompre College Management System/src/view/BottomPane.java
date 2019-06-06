package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.College;
import utils.Utils;

public class BottomPane {
	private HBox buttonBox;
	
	private Button studentBtn;
	private Button facultyBtn;
	private Button textbookBtn;
	private Button classroomBtn;
	private Button backupBtn;
	private Button restoreBtn;
	private Button exitBtn;
	
	private Alert exitConfirm;
	private Alert exitWarning;
	private Optional <ButtonType> result;
	
	private BorderPane root;
	private College college;
	
	private boolean isBackup;
	public BottomPane(College college, BorderPane root) {
		isBackup = false;
		this.root = root;
		this.college = college;
		makeBtns();
		makeAlerts();
		makeBoxes();
		assembleChildren();
		setBtnListener();
	}

	private void assembleChildren() {
		buttonBox.getChildren().addAll(studentBtn, facultyBtn, textbookBtn,
				classroomBtn, backupBtn, restoreBtn, exitBtn);
		buttonBox.setPadding(new Insets(10));
		buttonBox.setAlignment(Pos.BASELINE_CENTER);	
	}

	private void makeBoxes() {
		buttonBox = new HBox(30);	
	}
	
	private void makeAlerts() {
		exitConfirm = new Alert(AlertType.CONFIRMATION);
		exitConfirm.getButtonTypes().removeAll(ButtonType.OK, ButtonType.CANCEL);
		exitConfirm.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		exitConfirm.setTitle("Exit College Management Systyem" );
		exitConfirm.setHeaderText("Are you sure you want to exit?");
		
		exitWarning = new Alert(AlertType.WARNING);
		exitWarning.getButtonTypes().remove(ButtonType.OK);
		exitWarning.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		exitWarning.setTitle("Exit College Management Systyem" );
		exitWarning.setHeaderText("Warning: unsaved data will be lost.\nAre you sure you want to exit?");
	}

	private void makeBtns() {
		studentBtn = new Button("Student");
		studentBtn.setPrefSize(80, 25);
		
		facultyBtn = new Button("Faculty");
		facultyBtn.setPrefSize(80, 25);
		
		textbookBtn = new Button("Textbook");
		textbookBtn.setPrefSize(85, 25);
		
		classroomBtn = new Button("Classroom");
		classroomBtn.setPrefSize(90, 25);
		
		backupBtn = new Button("BACKUP");
		backupBtn.setPrefSize(80, 25);
		
		restoreBtn = new Button("RESTORE");
		restoreBtn.setPrefSize(85, 25);
		
		exitBtn = new Button("EXIT");
		exitBtn.setPrefSize(60, 25);
	}
	
	private void setBtnListener() {
		studentBtn.setOnAction(e -> {
			StudentPane studentPane = new StudentPane(college);
			root.setCenter(studentPane.getStudentBox());
		});
		
		facultyBtn.setOnAction(e -> {
			FacultyPane facultyPane = new FacultyPane(college);
			root.setCenter(facultyPane.getFacultyBox());
		});
		
		classroomBtn.setOnAction(e -> {
			ClassroomPane classroomPane = new ClassroomPane(college);
			root.setCenter(classroomPane.getClassroomBox());
		});
		
		textbookBtn.setOnAction(e -> {
			TextbookPane textbookPane = new TextbookPane(college);
			root.setCenter(textbookPane.getTextbookBox());
		});
		
		backupBtn.setOnAction(e -> {
			Utils.backup(college);
			isBackup = true;
		});
		
		restoreBtn.setOnAction(e -> {
			Utils.restore(college);
		});
		
		exitBtn.setOnAction(e -> {
			exitConfirm.setTitle("Exit College Management Systyem" );
			exitConfirm.setHeaderText("Are you sure you want to exit?");
			result = exitConfirm.showAndWait();
			exitConfirm.close();
			if (result.get() == ButtonType.YES) {
				if (!isBackup) {
					result = exitWarning.showAndWait();
					exitWarning.close();
					if (result.get() == ButtonType.YES) {
						Platform.exit();
					}
				}
				else {
					Platform.exit();
				}
			}
		});
	}
	
	public HBox getButtonBox() {
		return buttonBox;
	}
}