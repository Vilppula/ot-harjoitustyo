package laatikkopeli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import laatikkopeli.dao.DBUserDao;
import laatikkopeli.domain.User;

//Controls class for signup-view-fxml
public class SignupController implements Initializable {

    private DBUserDao userDao;
    private FrontPageController FPC;
    
    @FXML VBox signupMain;            //Content of the signup-view
    @FXML TextField username;       
    @FXML TextField password;
    @FXML Button createNew;
    @FXML Button returnToFrontpage;
    @FXML Label infoText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.infoText.setText("");
    }
    
    @FXML
    private void closeSignupView() throws IOException {
        signupMain.setVisible(false);
    }
    
    @FXML
    private void addNewPlayer() throws IOException {
        
        //If either of the fields is empty, return
        if (username.getText().isEmpty() || password.getText().isEmpty()) return;
        //Collect user from textfields
        User newUser = new User(username.getText(), password.getText());
        
        if (this.FPC == null) loadFPC();
        
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
            this.FPC.setUser1(newUser);
            this.FPC.openSinglePlayer();
        }
    }
 
    
    private void loadFPC() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frontpage.fxml"));
            Parent root = loader.load();
            this.FPC = loader.getController();
        } catch (IOException ex) {
            return;
        }
    }
}