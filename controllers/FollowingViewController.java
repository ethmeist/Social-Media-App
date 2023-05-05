package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;

public class FollowingViewController implements Initializable{

	@FXML
	private VBox postsContainer;
	private Stage stage;
	private Scene scene;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for (Post post : AccountCenter.getInstance().getThisUser().getMeFriend()) {
				FXMLLoader load = new FXMLLoader();
				load.setLocation(getClass().getResource("/fxml/Post.fxml"));
				VBox vbox = load.load();
				PostController pc = load.getController();
				pc.setData(post);
				postsContainer.getChildren().add(vbox);
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	public void goToMain(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
