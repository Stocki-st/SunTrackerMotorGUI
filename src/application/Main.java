package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane mainPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("MotorControl.fxml"));
			primaryStage.setScene(new Scene(mainPane));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
