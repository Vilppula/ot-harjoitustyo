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
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import laatikkopeli.dao.GameAreaDao;
import laatikkopeli.domain.GameLayout;
import laatikkopeli.domain.GameRunner;
import laatikkopeli.domain.User;


public class GameViewController implements Initializable {

    private FrontPageController FPC;    //Connection to parent controller
    private User user1, user2;
    private GamegridController GGC;     //Child controllers
    private GameInfoController GIC;     //--"--
    private GameRunner gameRunner;      //Game logic
    private GameAreaDao areas;
    private GameLayout gameLayout;
    
    @FXML StackPane gameView;                                                   //Main container (display gameChoose or gameplay here)
    @FXML SplitPane gameplay;
    @FXML VBox chooseGameView;
    @FXML VBox gameAreaList;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {                        //Init gameView. Show gameChoose first.
        this.chooseGameView.setVisible(true);
        this.gameplay.setVisible(false);
        this.gameplay.addEventFilter(KeyEvent.KEY_PRESSED,(event -> {           //Add filter to gameplay-node
            this.gameRunner.checkKey(event.getCode());                          //...which catches keypresses and notify GameRunner of them
        }));
       this.areas = new GameAreaDao();                                          //Create game area service (to load saved gameareas)
    }    
    
    public void closeGameView() {                                               //Method to close this view
        this.gameView.setVisible(false);
        this.FPC.switchTopButtons();
        this.FPC.switchMain();
    }
    
    public void reloadCurrent(){                                                //Reload game layout which is currently referenced in this gameview
        setUpGameplayView();
    }
    
    // CHOOSE GAME HERE ========================================================
    public void setUpSinglePlayerView(User user) {
        this.user1 = user;
        loadGameAreas();
    }
    
    public void setUpTwoPlayerView(User user1, User user2) {
        this.user1 = user1; this.user2 = user2;
        loadGameAreas();
    }
    
    public void loadGameAreas() {
        ButtonCreator butt = new ButtonCreator(100,780,8);
        for (GameLayout gameLayout: this.areas.getAll()) {
            System.out.println("Ladataan kenttä "+gameLayout.getName());
            HBox box = butt.create(gameLayout);
            Button button = (Button) box.getChildrenUnmodifiable().get(0);
            button.setOnAction((event)->{
                setUpGameplayView(gameLayout);
            });
            gameAreaList.getChildren().add(box);
        }
    }
    
    //START GAME HERE ==========================================================
    public void setUpGameplayView(GameLayout layout) {                          //Overloaded method for the following one
        this.gameLayout = layout;
        setUpGameplayView();
    }
    
    public void setUpGameplayView() {                                           //Set up a game based on game layout
        try {
            this.chooseGameView.setVisible(false);                              //Hide 'choose game' -view
            this.gameplay.setVisible(true);                                     //Show gameplay view
            this.gameplay.getItems().clear();                                   //Clear possible previous nodes from gameplay (fresh start)
            
            //Load gameInfo
            FXMLLoader infoloader = new FXMLLoader(getClass().getResource(
                    "/fxml/game/gameInfo.fxml"));
            Parent gameInfoView = infoloader.load();
            this.gameplay.getItems().add(gameInfoView);
            this.GIC = infoloader.getController();
            this.GIC.loadGVC(this);
            if (this.user2 == null)
                GIC.setUpSinglePlayerInfoView(this.user1);
            else GIC.setUpTwoPlayerInfoView(this.user1, this.user2);
            
            //Load gamegrid
            FXMLLoader gridloader = new FXMLLoader(getClass().getResource(
                    "/fxml/game/gamegrid.fxml"));
            Parent gameGridView = gridloader.load();
            this.gameplay.getItems().add(gameGridView);
            this.GGC = gridloader.getController();
            GGC.loadGVC(this);
            
            //Create GameRunner (and connect FXML to game logic)
            GGC.loadGIC(GIC); GIC.loadGGC(GGC);
            this.gameRunner = new GameRunner(this.GGC, this.GIC, this);
            this.gameRunner.startGame(this.gameLayout);
            
        } catch (IOException ex) {
            Logger.getLogger(GameViewController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void loadFPC(FrontPageController FPC) {                              //Get supercontroller here (FrontPageController)
        this.FPC = FPC;
    }
    
}
