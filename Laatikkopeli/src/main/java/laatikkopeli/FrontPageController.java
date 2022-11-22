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

    @FXML HBox buttonBar;
    @FXML Pane mainArea;
    @FXML VBox signupView;
    @FXML SplitPane onePlayerView;
    
    
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
