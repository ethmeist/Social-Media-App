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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.Post;
import objects.Reactions;

public class FocusedPostController {
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
	    
	    @FXML
	    private VBox commentContainer;
	   // private List<Post> comments;
	    
	    
	    public void setData(Post post) {
	    	caption.setText(post.getCaption());
	    	commentLabel.setText(post.getCommentCounter() + "");
	    	date.setText(post.getDate());
	    	likeLabel.setText(post.getLikeCounter() + "");
	    	userPosted.setText(post.getUserPosted());
	    	
	    	if (!post.getComments().isEmpty()) {
	    		try {
	    			for (Post comment : post.getComments()) {
	    			FXMLLoader fxmlLoad = new FXMLLoader();
					fxmlLoad.setLocation(getClass().getResource("/fxml/Post.fxml"));
				
					VBox vBox = fxmlLoad.load();
					PostController postController = fxmlLoad.getController();
					postController.setData(comment);
					commentContainer.getChildren().add(vBox);
	    			}
	    		} catch (Exception e) {e.printStackTrace();}
	    	} 
	    }
	    
	    
	    
	    
	public void createComment(MouseEvent me) throws IOException {
		
		FXMLLoader fxLoad = new FXMLLoader();
		fxLoad.setLocation(getClass().getResource("/fxml/CreateComment.fxml"));
		Parent root = fxLoad.load();
		CreateCommentController control = fxLoad.getController();
		control.setData(this.caption.getText());
		
		this.stage = (Stage)((Node)me.getSource()).getScene().getWindow();
		this.scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
		
		
	}
	    
	    
	    
	    
	public void goBackToFeed(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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




	
}
