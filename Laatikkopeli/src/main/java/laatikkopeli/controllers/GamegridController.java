package laatikkopeli.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class GamegridController implements Initializable {

    private GameViewController GVC;
    private int p1x, p1y, p2x, p2y;
    
    public GamegridController() {
    }
    
    @FXML GridPane gameGrid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameGrid.getChildren().clear();
    }
    
    public ImageView addToGrid(int i, int j) {                                  //Read game layout and add elements to gridpane
        ImageView homebox = new ImageView();
        this.gameGrid.add(homebox, j, i);
        return homebox;
    }
    
    public void loadGVC(GameViewController GVC) {                               //Get supercontroller (gameview)
        this.GVC = GVC;
    }
}
