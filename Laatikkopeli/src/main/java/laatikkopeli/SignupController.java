package laatikkopeli;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SignupController {

    @FXML VBox main;
    
    @FXML
    private void closeSignup() throws IOException {
        main.setVisible(false);
    }
    
}