package laatikkopeli.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import laatikkopeli.domain.GameLayout;
import laatikkopeli.domain.User;


public class GameViewController implements Initializable {

    private FrontPageController FPC;
    private User user1, user2;
    
    String testLayout = 
         ",,,,,,,,,,,,,,,"
        +",,,,,,,,,,,,,,,"
        +",,#,,,,,##,,,,,"
        +",,#,,,,,,##,,,,"
        +",,#,,,,,,,##,,,"
        +",,#,,,,,,,,,,,,"
        +",,########,,,,,"
        +",,,,,,####,,,,,"
        +",,,,,,####,,,,,"
        +",,,,,,,,,,,,,,,"
        +",,,,,,,,##,,,,,"
        +",,,,,,,###,,,,,"
        +",,,,,,####,,,,,"
        +",,,,,###,,,,,,,"
        +",,,,,,,,,,,,,,,";
    
    private GameLayout testlevel = new GameLayout("testlevel", 15, testLayout);
    
    @FXML SplitPane gameView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameView.getItems().clear();
    }    
    
    public void closeGameView() {
        this.gameView.setVisible(false);
        this.FPC.switchTopButtons();
    }
    
    public void setUpSinglePlayerView(User user) {
        this.user1 = user;
        this.setUpGameView();
    }
    
    public void setUpTwoPlayerView(User user1, User user2) {
        this.user1 = user1; this.user2 = user2;
        this.setUpGameView();
    }
    
    public void setUpGameView() {          //Load gameinfoview and gamegridview
        try {
            //Load gameInfo
            this.gameView.getItems().clear();
            FXMLLoader infoloader = new FXMLLoader(getClass().getResource("/laatikkopeli/game/gameInfo.fxml"));
            Parent gameInfoView = infoloader.load();
            this.gameView.getItems().add(gameInfoView);
            GameInfoController GIC = infoloader.getController();
            GIC.loadGVC(this);
            if (this.user2 == null)
                GIC.setUpSinglePlayerInfoView(this.user1);
            else GIC.setUpTwoPlayerInfoView(this.user1, this.user2);
            
            //Load gamegrid
            FXMLLoader gridloader = new FXMLLoader(getClass().getResource("/laatikkopeli/game/gamegrid.fxml"));
            Parent gameGridView = gridloader.load();
            this.gameView.getItems().add(gameGridView);
            GamegridController GGC = gridloader.getController();
            GGC.loadGVC(this);
            GGC.setGame(this.testlevel);
        
        } catch (IOException ex) {
            Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadFPC(FrontPageController FPC) {
        this.FPC = FPC;
    }
    
}
