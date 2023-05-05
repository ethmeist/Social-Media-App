package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;
import objects.UserAccount;

public class UserFeedController {
	@FXML
    private Label followerCount;

    @FXML
    private Label followingCount;

    @FXML
    private Label numberOfPosts;
    @FXML
    private VBox myPostsContainer;
    

    @FXML
    private Label username;
    private Stage stage;
    private Scene scene;
    
    public void setData(UserAccount user) {
    	
    	
    	numberOfPosts.setText(user.getMyPosts().size() + "");
    	username.setText(user.getUsername());
    	followerCount.setText(user.getMyFollowers().size() + "");
    	followingCount.setText(user.getMyFollowing().size() + "");
    	try {
    		for (Post post : AccountCenter.getInstance().getThisUser().getMyPosts()) {
    			FXMLLoader fxmlLoad = new FXMLLoader();
				fxmlLoad.setLocation(getClass().getResource("/fxml/Post.fxml"));
				VBox vBox = fxmlLoad.load();
				PostController postController = fxmlLoad.getController();
				postController.setData(post);
				myPostsContainer.getChildren().add(vBox);
    		}
    	} catch (Exception e) {e.printStackTrace();}
    	
    }
    
    public void goBackToPostfeed(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
}
