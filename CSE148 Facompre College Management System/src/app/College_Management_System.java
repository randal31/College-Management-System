package app;

import config.BagConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.College;
import utils.Utils;
import view.BottomPane;

public class College_Management_System extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final int MAX_PERSON = BagConfiguration.getMaxPerson();
		final int MAX_TEXTBOOK = BagConfiguration.getMaxTextbook();
		final int MAX_CLASSROOM = BagConfiguration.getMaxClassroom();
		final int MAX_COURSE = BagConfiguration.getMaxCourse();
		
		College college = new College(MAX_PERSON, MAX_TEXTBOOK, MAX_CLASSROOM, MAX_COURSE);
		Utils.restore(college);
		BorderPane root = new BorderPane();
		BottomPane bottomPane = new BottomPane(college, root);
		root.setBottom(bottomPane.getButtonBox());
		
		Scene scene = new Scene(root, 1200, 700);
		
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("College Management System");
		primaryStage.show();
	}
}