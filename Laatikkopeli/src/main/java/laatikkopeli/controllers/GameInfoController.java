package laatikkopeli.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import laatikkopeli.domain.TimerHandler;
import laatikkopeli.domain.User;

public class GameInfoController implements Initializable {


    private User user1, user2;
    private GameViewController GVC;
    private GamegridController GGC;
    private TimerHandler timerHandler;
    private Timer timer;
    
    @FXML VBox player1Info, player2Info;
    @FXML Label player1Name, player2Name;
    @FXML Label timerLabel;
    @FXML Label player1Steps, player2Steps;
    @FXML Label player1MinSteps, player2MinSteps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1Info.setVisible(false);
        player2Info.setVisible(false);
        this.player2Info.setDisable(true);
        this.timerHandler = new TimerHandler(this.timerLabel);
        this.timer = new Timer();
        timer.schedule(timerHandler, 1000, 1000);
    }
    
    public void closeGame() {
        this.GVC.closeGameView();
    }
    
    public void restartGame() {
        this.GVC.reloadCurrent();
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
    
    public void updateP1Counter() {
        this.player1Steps.setText(""+(Integer.valueOf(this.player1Steps.getText())+1));
    }
    public void updateP2Counter() {
        this.player2Steps.setText(""+(Integer.valueOf(this.player2Steps.getText())+1));
    }
    
    public void switchTurn() {
        if (this.player1Info.isDisable() == true) {
            this.player1Info.setDisable(false);
            this.player2Info.setDisable(true);
        } else {
            this.player1Info.setDisable(true);
            this.player2Info.setDisable(false);
        }
    }
    
    public void updateTimer() {
        //Update timer label here
    }
    
    public void setPlayer1Min(int steps) {
        this.player1MinSteps.setText(""+steps);
    }
    
    public void loadGVC(GameViewController GVC) {
        this.GVC = GVC;
    }
    
    public void loadGGC(GamegridController GGC) {
        this.GGC = GGC;
    }
}
