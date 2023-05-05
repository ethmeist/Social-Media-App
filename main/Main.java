package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.PostCenter;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/WelcomeInterface.fxml"));
		stage.setTitle("yapper");
		stage.setScene(new Scene(root));
		stage.show();
		stage.setOnCloseRequest(e -> {
			AccountCenter.getInstance().save();
			PostCenter.getInstance().save();
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
