package laatikkopeli.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import laatikkopeli.domain.GameLayout;


public class GamegridController implements Initializable {

    private GameViewController GVC;
    
    public GamegridController() {
    }
    
    @FXML GridPane gameGrid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setGame(GameLayout level) {
        for (int i = 0; i < level.getSize(); i++) {
            for (int j = 0; j < level.getSize(); j++) {
                Image newImage = pickImage(level.getLayout().charAt(level.getSize()*i+j));
                this.gameGrid.add(new ImageView(newImage), j, i);
            }
        }
    }
    
    public Image pickImage(char c) {
        switch (c) {
            case ',': return new Image("/images/blank.png");
            case '#':
                Random rnd = new Random();
                int n = rnd.nextInt(4);
                return new Image("/images/wall"+n+".png");
        }
        return null;
    }
    
    public void loadGVC(GameViewController GVC) {
        this.GVC = GVC;
    }
}
