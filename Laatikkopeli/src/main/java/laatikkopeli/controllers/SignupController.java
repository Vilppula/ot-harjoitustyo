package laatikkopeli.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import laatikkopeli.domain.User;

//Controls class for signup-view-fxml
public class SignupController implements Initializable {

    private FrontPageController FPC;
    
    @FXML VBox signupMain;            //Content of the signup-view
    @FXML TextField username;       
    @FXML TextField password;
    @FXML Button createNew;
    @FXML Button returnToFrontpage;
    @FXML Text infoText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.infoText.setText("");
    }
    
    @FXML
    private void closeSignupView() throws IOException {
        signupMain.setVisible(false);
        FPC.switchMain();
    }
    
    @FXML
    private void addNewPlayer() throws IOException {
        //If either of the fields is empty, return
        if (username.getText().isEmpty() || password.getText().isEmpty()) return;
        //Collect user from textfields
        User newUser = new User(username.getText(), password.getText(), "/images/defaultAV.png");
        boolean success = this.FPC.getUserDao().saveUser(newUser);
        if (!success) {
            this.infoText.setText("Käyttäjä "+newUser.getUsername()+" on jo olemassa.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> 
                    this.infoText.setText("")
            );
            pause.play();
            this.username.setText(""); this.password.setText("");
        }
        else {
            this.signupMain.setVisible(false);
            this.FPC.setUser(newUser);
        }
    }
    
    public void loadFPC(FrontPageController controller) {
       this.FPC = controller;
    }
}