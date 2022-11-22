package laatikkopeli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FrontPageController implements Initializable {

    @FXML HBox buttonBar;   //Below main title: 3 buttons
    @FXML Pane mainArea;    //Main view. Changes by pressing buttons on "buttonbar"
    @FXML VBox signupView;  //This view will be opened inside main view for new player
    @FXML SplitPane onePlayerView;  //1P view to be set visible inside main view
    
    
    @FXML
    public void openSignup() throws IOException {
        this.signupView.setVisible(true);
    }

    public HBox getButtonBar() {
        return buttonBar;
    }

    public Pane getMainArea() {
        return mainArea;
    }

    public VBox getSignupView() {
        return signupView;
    }

    public SplitPane getOnePlayerView() {
        return onePlayerView;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.signupView.setVisible(false);
        this.onePlayerView.setVisible(false);
    }
}
