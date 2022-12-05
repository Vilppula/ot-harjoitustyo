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
import javafx.scene.input.KeyEvent;
import laatikkopeli.domain.GameLayout;
import laatikkopeli.domain.GameRunner;
import laatikkopeli.domain.User;


public class GameViewController implements Initializable {

    private FrontPageController FPC;    //Connection to parent controller
    private User user1, user2;
    private GamegridController GGC;     //Child controllers
    private GameInfoController GIC;     //--"--
    private GameRunner gameRunner;      //Game logic
    
    
    String testLayout =                 
         ",,,1,,,,,,,,,,,"
        +",,,,,,,,5,,,,,,"
        +",,#,,,,,##,,,,,"
        +",,#,,,,,,##,,,,"
        +",,#,,3,,,,##,,,"
        +",,#,,,,,,,,,,,,"
        +",,########,,,,,"
        +",,,,,,####,,,,,"
        +",,,,,,####,,4,,"
        +",,,,,,,,,,,,,,,"
        +",,,,,,,,##,,2,,"
        +",,,6,,,###,,,,,"
        +",,,,,,####,,,,,"
        +",,,,,###,,,,,,,"
        +",,,,,,,,,,,,,,,";
    
    private GameLayout testlevel = new GameLayout("testlevel", 15, testLayout); //Use this until game chooser have been built
    
    @FXML SplitPane gameView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameView.getItems().clear();
        this.gameView.addEventFilter(KeyEvent.KEY_PRESSED,(event -> {           //Catch keypresses and call for GameRunner to act accordingly
            this.gameRunner.checkKey(event.getCode());
        }));
    }    
    
    public void closeGameView() {
        this.gameView.setVisible(false);
        this.FPC.switchTopButtons();
    }
    
    public void openGameChooser(){
        //Coming soon...
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
            this.gameView.getItems().clear();
            
            //Load gameInfo
            FXMLLoader infoloader = new FXMLLoader(getClass().getResource("/laatikkopeli/game/gameInfo.fxml"));
            Parent gameInfoView = infoloader.load();
            this.gameView.getItems().add(gameInfoView);
            this.GIC = infoloader.getController();
            this.GIC.loadGVC(this);
            if (this.user2 == null)
                GIC.setUpSinglePlayerInfoView(this.user1);
            else GIC.setUpTwoPlayerInfoView(this.user1, this.user2);
            
            //Load gamegrid
            FXMLLoader gridloader = new FXMLLoader(getClass().getResource("/laatikkopeli/game/gamegrid.fxml"));
            Parent gameGridView = gridloader.load();
            this.gameView.getItems().add(gameGridView);
            this.GGC = gridloader.getController();
            GGC.loadGVC(this);
            
            //Create GameRunner (and connect FXML to game logic)
            this.gameRunner = new GameRunner(this.GGC, this.GIC, this);
            this.gameRunner.createGameArea(testlevel);
            
        } catch (IOException ex) {
            Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadFPC(FrontPageController FPC) {      //Get supercontroller here (FrontPageController)
        this.FPC = FPC;
    }
    
}
