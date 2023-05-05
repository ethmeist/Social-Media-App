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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.AccountCenter;

public class WelcomeController {


	    private Stage stage;
	    private Scene scene;
	    
	    
	    public void switchToSignUpView(ActionEvent e) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUpView.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    
	    public void switchToSignInView(ActionEvent e) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/WelcomeInterface.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	    @FXML
	    private PasswordField confirmPassword;
	    @FXML
	    private PasswordField signUpPassword;
	    @FXML
	    private TextField signUpUsername;
	    @FXML
	    private Label signUpOutput;
	    
	    public void createAccount(ActionEvent e) {
	    	if (signUpUsername.getText().length()-1 <= 3 || signUpPassword.getText().length()-1 <=4) {
	    		signUpOutput.setStyle("-fx-alignment: center;");
				signUpOutput.setText("username/password gotta be at least 6 characters");
	    	}
	    	else if (!(signUpPassword.getText().equals(confirmPassword.getText()))) {
	    		signUpOutput.setStyle("-fx-alignment: center;");
				signUpOutput.setText("passwords don't match :(");
	    	}
	    	else if (signUpUsername.getText().length() <= 1) {
	    		signUpOutput.setStyle("-fx-alignment: center;");
	    		signUpOutput.setText("username field cannot be empty !");
	    	}
	    	else if (AccountCenter.getInstance().exists(signUpUsername.getText())) {
	    		signUpOutput.setStyle("-fx-alignment: center;");
	    		signUpOutput.setText("sorry, that username is taken");
	    	}
	    	else {
	    		AccountCenter.getInstance().addUser(signUpUsername.getText(), signUpPassword.getText());
	    		signUpOutput.setStyle("-fx-alignment: center;");
	    		signUpOutput.setText("Success! Account Created");
	    	}
	    }
	 	@FXML
	    private PasswordField passwordInput;
	    @FXML
	    private Button signIn;
	    @FXML
	    private TextField usernameInput;
	    @FXML
	    private Label signInOutput;
	    
	    
	    
	    public void logIn (ActionEvent e) throws IOException {
	    	if (AccountCenter.getInstance().validate(usernameInput.getText(), passwordInput.getText())) {
	    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PostfeedView.fxml"));
				stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
	    	}
	    	//else if () {}
	    	else {
	    		signInOutput.setText("Login failed");
	    	}
	    }
	    
}
