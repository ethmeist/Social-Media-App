package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;
import objects.UserAccount;

public class UserProfileController {
    @FXML
    private Label focusedUser;

    @FXML
    private Label followerCount;

    @FXML
    private Label followingCount;

    @FXML
    private VBox postsContainer;
    @FXML
    private Button followButton;
    private Stage stage;
    private Scene scene;
    
    
    public void setData(UserAccount user) {
    	
    	if (user.getUsername().equals(AccountCenter.getInstance().getThisUser().getUsername())) {
    		followButton.setVisible(false);
    	}
    	else if (AccountCenter.getInstance().getThisUser().isFollowing(user.getUsername())) {
    		followButton.setText("unfollow");
    	}
    	else {
    		followButton.setText("follow");
    	}
    	
    	
    	focusedUser.setText(user.getUsername());
    	followerCount.setText(user.getMyFollowers().size() + "");
    	followingCount.setText(user.getMyFollowing().size() + "");
    	
    	
    	try {
    		for (Post post : user.getMyPosts()) {
    			FXMLLoader load = new FXMLLoader();
    			load.setLocation(getClass().getResource("/fxml/Post.fxml"));
    			
    			VBox vbox = load.load();
    			PostController pc = load.getController();
    			pc.setData(post);
    			postsContainer.getChildren().add(vbox);
    		}
    	} catch (Exception e) {e.printStackTrace();}
    	
    }
    
    
    
    public void followAction (ActionEvent e) {
    	UserAccount followUser = AccountCenter.getInstance().findUser(focusedUser.getText());
    	
    	if (AccountCenter.getInstance().getThisUser().isFollowing(followUser.getUsername())) {
    	
    		AccountCenter.getInstance().getThisUser().getMyFollowing().remove(followUser);
    		followUser.getMyFollowers().remove(AccountCenter.getInstance().getThisUser());
    		followerCount.setText(followUser.getMyFollowers().size() + "");
    		followButton.setText("follow");
    		return;
    	}
 
    	if (!AccountCenter.getInstance().getThisUser().isFollowing(followUser.getUsername())){
    		AccountCenter.getInstance().getThisUser().getMyFollowing().add(followUser); //add this user to our following
    		followUser.getMyFollowers().add(AccountCenter.getInstance().getThisUser()); //add me to the users followers
    		followerCount.setText(followUser.getMyFollowers().size() + "");
    		followButton.setText("unfollow");
    		return;
    	}
    }
    
    
    public void switchToHome(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
}
