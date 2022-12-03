package laatikkopeli.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import laatikkopeli.domain.User;


public class SinglePlayerViewController implements Initializable {

    private AnchorPane gamegrid;                                //Gamegrid area
    private AnchorPane gameInfo;                                //Gameinfo area
    private User user;                                          //User of this singlePlayer instance
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void showGamegrid() {
        this.gamegrid.setVisible(true);
    }
    
    public void hideGamegrid() {
        this.gamegrid.setVisible(false);
    }

    public void showPlayerData() {
        
    }
}
