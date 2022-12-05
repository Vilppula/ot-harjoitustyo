package laatikkopeli.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import laatikkopeli.domain.TimerHandler;
import laatikkopeli.domain.User;

public class GameInfoController implements Initializable {


    private User user1, user2;
    private GameViewController GVC;
    private GamegridController GGC;
    private TimerHandler timerHandler;
    private Timer timer;
    
    @FXML VBox player1Info;                 //FXML elements
    @FXML VBox player2Info;
    @FXML Label player1Name;
    @FXML Label player2Name;
    @FXML Label timerLabel;
    @FXML ImageView player1Avatar;
    @FXML ImageView player2Avatar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1Info.setVisible(false);
        player2Info.setVisible(false);
        this.timerHandler = new TimerHandler(this.timerLabel);
        this.timer = new Timer();
        timer.schedule(timerHandler, 1000, 1000);
    }
    
    public void closeGame() {
        this.GVC.closeGameView();
    }
    
    public void restartGame() {
        this.GVC.setUpGameView();
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }
    
    public void setUpSinglePlayerInfoView(User user) {                          
        this.user1 = user;
        this.player1Name.setText(this.user1.getUsername());
        this.player1Info.setVisible(true);
    }
    
    public void setUpTwoPlayerInfoView(User user1, User user2) {
        this.setUpSinglePlayerInfoView(user1);
        this.user2 = user2;
        this.player2Name.setText(this.user2.getUsername());
        this.player2Info.setVisible(true);
    }
    
    public void updateTimer() {
        //Update timer label here
    }
    
    public void loadGVC(GameViewController GVC) {
        this.GVC = GVC;
    }
}
