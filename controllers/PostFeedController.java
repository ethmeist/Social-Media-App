package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;
import objects.PostCenter;

public class PostFeedController implements Initializable{

	@FXML
	private VBox postContainer;
	@FXML
	private Label userLabel;
	private Stage stage;
	private Scene scene;
	List<Post> posts;
	
	public void switchToCreatePost(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateNewPost.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToUserFeed(ActionEvent e) throws IOException {
		FXMLLoader load = new FXMLLoader();
		load.setLocation(getClass().getResource("/fxml/UserFeed.fxml"));
		Parent root = load.load();
		UserFeedController uf = load.getController();
		uf.setData(AccountCenter.getInstance().getThisUser());
		
		this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void switchToFollowingFeed(ActionEvent e) throws IOException {
		FXMLLoader load = new FXMLLoader();
		load.setLocation(getClass().getResource("/fxml/FollowingView.fxml"));
		Parent root = load.load();
		this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public List<Post> getPosts(){
		return PostCenter.getInstance().getAllPosts();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		posts = new LinkedList<>(getPosts());
		userLabel.setText(AccountCenter.getInstance().getThisUser().getUsername());
		try {
			for (Post post : posts) {
				FXMLLoader fxmlLoad = new FXMLLoader();
				fxmlLoad.setLocation(getClass().getResource("/fxml/Post.fxml"));
			
				VBox vBox = fxmlLoad.load();
				PostController postController = fxmlLoad.getController();
				postController.setData(post);
				postContainer.getChildren().add(vBox);
			}
		}
		catch (Exception e) {e.printStackTrace();}
		
	}
		
	
	public void logOut(ActionEvent e) throws IOException {
		FXMLLoader load = new FXMLLoader();
		load.setLocation(getClass().getResource("/fxml/WelcomeInterface.fxml"));
		Parent root = load.load();
		this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}

}
