package laatikkopeli.gui;

import laatikkopeli.SignupController;
import javafx.fxml.FXMLLoader;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SignupControllerTest {
    
    SignupController signupController;
    FXMLLoader loader;
    
    @Before
    public void setUp() {
        this.loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        this.signupController = loader.getController();
    }
}
