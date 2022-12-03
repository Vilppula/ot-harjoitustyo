package laatikkopeli.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import laatikkopeli.domain.User;

public class LoginController implements Initializable {

    private FrontPageController FPC;
    
    @FXML AnchorPane loginMain;
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Label infoText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.infoText.setText("");
    }    
    
    @FXML
    public void closeLoginView() {
        this.loginMain.setVisible(false);
    }
    
    @FXML
    private void loginAsPlayer() throws IOException {
        if (username.getText().isEmpty() || password.getText().isEmpty()) return;       //If either of the fields is empty, return
        User newUser = new User(username.getText(), password.getText());                //Collect user from textfields
        User foundUser = this.FPC.getUserDao().findUser(newUser);                       //Ask user from DB
        User current = FPC.getUser1();
        if (foundUser == null) {
            showMessage("Pelaajaa '"+newUser.getUsername()+"' ei löydy."); 
        } else if (current != null && current.equals(foundUser)) { 
            showMessage("Pelaaja '"+current.getUsername()+"' on jo kirjautunut.");
            this.password.setText(""); this.username.setText("");
        } else if (!foundUser.getPassword().equals(newUser.getPassword())) {
            showMessage("Väärä salasana. (oli '" + newUser.getPassword() + "', pitäisi olla '" + foundUser.getPassword() + "')");
            this.password.setText("");
        } else {
            this.loginMain.setVisible(false);
            this.FPC.setUser(newUser);
        }
    }
    
    private void showMessage(String message) {
        this.infoText.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> 
                this.infoText.setText("")
        );
        pause.play();
    }
    
    public void loadFPC(FrontPageController controller) {
       this.FPC = controller;
    }
}
