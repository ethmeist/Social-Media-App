package controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.PostCenter;
import objects.Utils;

public class CreatePostController {

	@FXML
	private TextArea captionInput;
	
	private Stage stage;
	private Scene scene;
	
	public void createNewPost(ActionEvent e) throws IOException {
		if (!captionInput.getText().isEmpty()) {
			PostCenter.getInstance().addNewPost(captionInput.getText());
			AccountCenter.getInstance().getThisUser().getMyPosts().addFirst(PostCenter.getInstance().findPost(captionInput.getText()));
			//AccountCenter.getInstance().getThisUser().getMyPosts().ad
		}
		//switch scene
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goBackToFeed(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void performSpellCheck(ActionEvent e) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Spell Checker");
		if (!captionInput.getText().isEmpty()) {
			LinkedList<String> misspelled = Utils.spellCheck(captionInput.getText());
			if (misspelled.isEmpty()) {
				//System.out.println("good spell");
				alert.setContentText("No spelling errors detected");
				
			}
			else {
				alert.setContentText("These word(s) might be mispelled: \n ");
				for (String string : misspelled) {
					alert.setContentText(alert.getContentText() + string +", ");
				}
			}
			alert.show();
		} else {
			alert.setContentText("at least type something first");
			alert.show();
		}
	}
}
