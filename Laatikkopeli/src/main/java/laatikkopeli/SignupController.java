package laatikkopeli;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import laatikkopeli.domain.User;

//Controls class for signup-view-fxml
public class SignupController {

    @FXML VBox main;            //Content of the signup-view
    @FXML TextField username;       
    @FXML TextField password;
    @FXML Button createNew;
    @FXML Button returnToFrontpage;
    
   
    @FXML
    private void closeSignup() throws IOException {
        main.setVisible(false);
    }
    
    @FXML
    private boolean addNewPlayer() throws IOException {
        if (username.getText().isEmpty() || password.getText().isEmpty()) return false;
        User newUser = new User(username.getText(), password.getText());
        System.out.println("Luotiin käyttäjä "+newUser.getUsername()+" : "+newUser.getPassword());
        username.setText(""); password.setText("");
        return true;
    }
    
}