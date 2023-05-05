package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.AccountCenter;
import objects.Post;
import objects.PostCenter;
import objects.Reactions;
import objects.UserAccount;


public class PostController {
    @FXML
    private Label caption;

    @FXML
    private Label commentLabel;

    @FXML
    private Label date;

    @FXML
    private Label likeLabel;

    @FXML
    private Label userPosted;
    @FXML
    private ImageView imgLike;
    private Stage stage;
    private Scene scene;
    
    public void setData(Post post) {
    	userPosted.setText(post.getUserPosted());
    	date.setText(post.getDate());
    	caption.setText(post.getCaption());
    	likeLabel.setText(post.getLikeCounter() + "");
    	commentLabel.setText(post.getCommentCounter() + "");
    }
    
    
    private Reactions currentReaction;
	@FXML
	public void onLikeContainerPressed(MouseEvent me) {
	int likes = Integer.parseInt(likeLabel.getText());
	if (currentReaction != Reactions.LIKE) {
		setReaction(Reactions.LIKE);
		likeLabel.setText(likes+1 + "");
	}
	else {
		setReaction(Reactions.NON);
		likeLabel.setText(likes-1 + "");
	}
	
	}
	
	private void setReaction(Reactions reaction) {
		Image image = new Image(getClass().getResourceAsStream(reaction.getImgSource()));
		imgLike.setImage(image);
		currentReaction = reaction;
	}
	
	public void openView(ActionEvent e) throws IOException {
		Post thisPost = PostCenter.getInstance().findPost(this.caption.getText());
		
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
	
	public void openUserProfile (MouseEvent me) throws IOException {
		UserAccount thisUser = AccountCenter.getInstance().findUser(userPosted.getText());
		
		FXMLLoader load = new FXMLLoader();
		load.setLocation(getClass().getResource("/fxml/UserProfileView.fxml"));
		Parent root = load.load();
		UserProfileController upc = load.getController();
		upc.setData(thisUser);
		
		//switch scene
		this.stage = (Stage)((Node)me.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	
}
