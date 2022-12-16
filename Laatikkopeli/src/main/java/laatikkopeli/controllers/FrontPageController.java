package laatikkopeli.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import laatikkopeli.dao.DBUserDao;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.User;

public class FrontPageController implements Initializable {

    private DBhandler dbhandler;
    private DBUserDao userDao;
    private User user1;
    private User user2;
    
    @FXML AnchorPane topButtons;                                                //Below main title: 3 buttons
    @FXML AnchorPane mainArea;                                                  //Main view. Changes by pressing buttons on "buttonbar"
    @FXML ImageView mainImage;

    // Player related buttons and views
    @FXML HBox showPlayer1;                                                     //Contains name of logged in player1 + login/logout buttons
    @FXML HBox showPlayer2;                                                     //Contains name of logged in player2 + login/logout buttons
    @FXML StackPane p1Buttons;                                                  //Switch login/logout P1
    @FXML StackPane p2Buttons;                                                  //Switch login/logout P2
    @FXML Label player1Name;        
    @FXML Label player2Name;
    @FXML Button loginP1;
    @FXML Button logoutP1;
    @FXML Button loginP2;
    @FXML Button logoutP2;
    @FXML ImageView player1Avatar;
    @FXML ImageView player2Avatar;

    //Center buttons
    @FXML Button play1Button;
    @FXML Button signUpButton;
    @FXML Button play2Button;
    
    public FrontPageController(){
        this.dbhandler = new DBhandler("laatikkopeli");                         //Create dbhandler opening database "laatikkopeli.db"
        this.userDao = new DBUserDao(this.dbhandler);                           //DAO for user data (using dbhandler as utility)
    }
    
    //========================================================================== OPEN VIEWS
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showPlayer1.setVisible(false);
        this.showPlayer2.setVisible(false);
        this.loginP2.setDisable(true);
        this.mainArea.setVisible(false);
        this.mainImage.setImage(new Image("/images/Main.png"));
    }
    
    @FXML
    public void openLoginView() throws IOException {                            //Load login.fxml inside 'mainArea'-AnchorPane
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/login.fxml"));
        Parent loginview = loader.load();
        this.mainArea.getChildren().clear();
        this.mainArea.getChildren().add(loginview);
        switchMain();
        LoginController logincontroller = loader.getController();
        logincontroller.loadFPC(this);
    }
    
    @FXML
    public void openSignupView() throws IOException {                            //Load signup.fxml inside 'mainArea'-pane
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/signup.fxml"));
        Parent signupview = loader.load();
        this.mainArea.getChildren().clear();
        this.mainArea.getChildren().add(signupview);
        switchMain();
        SignupController signupcontroller = loader.getController();
        signupcontroller.loadFPC(this);
    }
    
    @FXML
    public void openSinglePlayerView() throws IOException {
        this.switchTopButtons();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/game/gameView.fxml"));
        Parent gameView = loader.load();
        this.mainArea.getChildren().clear();
        this.mainArea.getChildren().add(gameView);
        switchMain();
        GameViewController gameViewController = loader.getController();
        gameViewController.loadFPC(this);
        gameViewController.setUpSinglePlayerView(this.user1);
    }
    
    @FXML
    public void openTwoPlayerView() throws IOException {
        this.switchTopButtons();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/game/gameView.fxml"));
        Parent gameView = loader.load();
        this.mainArea.getChildren().clear();
        this.mainArea.getChildren().add(gameView);
        switchMain();
        GameViewController gameViewController = loader.getController();
        gameViewController.loadFPC(this);
        gameViewController.setUpTwoPlayerView(this.user1, this.user2);
    }
    
    //========================================================================== HIDE/UNHIDE ELEMNTS
    public void switchTopButtons() {
        this.topButtons.setDisable(!this.topButtons.isDisable());
    }
    
    public void switchMain() {
        this.mainArea.setVisible(!this.mainArea.isVisible());
        //this.mainImage.setVisible(!this.mainImage.isVisible());
    }
    
    //========================================================================== LOGOUT METHODS
    public void logoutP1() {
        System.out.println("KIRJATAAN ULOS PELAAJA "+user1.getUsername());
        this.play2Button.setDisable(true);
        this.player2Name.setText("");
        if (this.user2 != null) {
            this.user1 = this.user2; this.user2 = null;
            this.player1Name.setText(this.user1.getUsername());
            this.logoutP2.setVisible(false); 
            this.loginP2.setVisible(true);
            this.player1Avatar.setImage(this.player2Avatar.getImage());
            this.player2Avatar.setImage(null);
        }
        else {
            this.user1 = null; 
            this.loginP2.setDisable(true);
            this.logoutP1.setVisible(false);
            this.loginP1.setVisible(true);
            this.player1Name.setText("");
            this.play1Button.setDisable(true);
            this.showPlayer1.setVisible(false);
        }
    }
    
    public void logoutP2() {
        System.out.println("KIRJATAAN ULOS PELAAJA "+user2.getUsername());
        this.user2 = null;
        this.player2Name.setText("");
        this.showPlayer2.setVisible(false);
        this.loginP2.setVisible(true);
        this.loginP2.setDisable(false);
    }

    //========================================================================== SHARE RESOURCES TO NESTED CONTROLLERS
    public DBUserDao getUserDao() {
        return userDao;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }
    
    //========================================================================== SET USERS (LOGIN/ SIGNUP)
    public void setUser(User user) {
        if (this.user1 != null && this.user2 != null) return;                   //If two players are already set (in case new player is signed up)
        System.out.println("KIRJAUDUTTIIN PELAAJANIMELLÄ "+user.getUsername());
        if (this.user1 == null) {                                               //If user1 is not set go here
            this.user1 = user;
            this.player1Name.setText(this.user1.getUsername());
            this.player1Avatar.setImage(new Image(user1.getAvatarURL()));
            this.showPlayer1.setVisible(true);
            this.loginP1.setVisible(false);
            this.logoutP1.setVisible(true);
            this.play1Button.setDisable(false);
            this.loginP2.setDisable(false);
        
        } else {                                                                //Else set user2
            this.user2 = user;
            this.player2Name.setText(this.user2.getUsername());
            this.player2Avatar.setImage(new Image(user2.getAvatarURL()));
            this.showPlayer2.setVisible(true); 
            this.loginP2.setVisible(false);
            this.logoutP2.setVisible(true);
            this.play2Button.setDisable(false);
        }
    }
}
