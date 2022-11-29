package laatikkopeli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import laatikkopeli.dao.DBUserDao;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.User;

public class FrontPageController implements Initializable {

    private DBhandler dbhandler;
    private DBUserDao userDao;
    private User user1;
    private User user2;
    
    @FXML HBox buttonBar;   //Below main title: 3 buttons
    @FXML Pane mainArea;    //Main view. Changes by pressing buttons on "buttonbar"
    @FXML VBox signupView;  //This view will be opened inside main view for new player
    @FXML AnchorPane loginView; //This view opens when 'kirjaudu'-button is fired
    @FXML SplitPane singlePlayerView;  //1P view to be set visible inside main view
    @FXML HBox signButtons;
    @FXML HBox playButtons;
    @FXML Label player1Name;
    
    public FrontPageController(){
        this.dbhandler = new DBhandler("laatikkopeli");                         //Create dbhandler opening database "laatikkopeli.db"
        this.userDao = new DBUserDao(this.dbhandler);                           //DAO for user data (using dbhandler as utility)
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hideAllInMainArea();
        this.playButtons.setVisible(false);
    }
    
    @FXML
    public void openSignupView() throws IOException {
        this.hideAllInMainArea();
        this.signupView.setVisible(true);
    }
    
    @FXML
    public void openSinglePlayer() throws IOException {
        this.hideAllInMainArea();
        this.singlePlayerView.setVisible(true);
    }
    
    @FXML
    public void openLoginView() throws IOException {
        this.hideAllInMainArea();
        this.loginView.setVisible(true);
    }

    @FXML
    public void hideAllInMainArea() {
        this.signupView.setVisible(false);
        this.singlePlayerView.setVisible(false);
        this.loginView.setVisible(false);
    }

    @FXML
    public void switchButtons() {
        this.playButtons.setVisible(!this.playButtons.isVisible());
        this.signButtons.setVisible(!this.signButtons.isVisible());
    }
    
    public DBUserDao getUserDao() {
        return userDao;
    }

    @FXML
    public void setUser1(User user1) {
        System.out.println("KIRJAUDUTTIIN PELAAJANIMELLÄ "+user1.getUsername());
        this.mainArea.setVisible(false);
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
    
    
}
