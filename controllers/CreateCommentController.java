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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;
import objects.PostCenter;
import objects.Utils;

public class CreateCommentController {

	@FXML
	private Label postSnip;
	@FXML
	private TextArea commentInput;
	private Scene scene;
	private Stage stage;
	
	public void setData(String caption) {
		postSnip.setText(caption);
	}
	
	public void goBackToFeed(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void createNewComment(ActionEvent e) throws IOException {
		Post thisPost = PostCenter.getInstance().findPost(postSnip.getText());
		Post comment = new Post();
		comment.setCaption(commentInput.getText());
		comment.setDate(Utils.getDate());
		comment.setUserPosted(AccountCenter.getInstance().getThisUser().getUsername());
		comment.setCommentCounter(0);
		comment.setLikeCounter(0);
		//add to comments/increment counter
		thisPost.getComments().add(comment);
		thisPost.setCommentCounter(thisPost.getCommentCounter()+1);
		//switch scene
		
		FXMLLoader load = new FXMLLoader();
		load.setLocation(getClass().getResource("/fxml/FocusedPost.fxml"));
		Parent root = load.load();
		FocusedPostController foc = load.getController();
		foc.setData(thisPost);
		
		this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void performSpellCheck(ActionEvent e) throws IOException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Spell Checker");
		if (!commentInput.getText().isEmpty()) {
			LinkedList<String> misspelled = Utils.spellCheck(commentInput.getText());
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
