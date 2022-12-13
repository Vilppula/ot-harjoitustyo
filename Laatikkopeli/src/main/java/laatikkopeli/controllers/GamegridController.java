package laatikkopeli.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import laatikkopeli.domain.User;


public class GamegridController implements Initializable {

    private GameViewController GVC;
    private GameInfoController GIC;
    
    @FXML GridPane gamegrid;
    @FXML VBox gamegridInfo;
    @FXML Text infoTopic;
    @FXML Label infoDescription;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gamegrid.getChildren().clear();
        this.gamegridInfo.setVisible(false);
    }
    
    public ImageView addToGrid(int i, int j) {                                  //Read game layout and add elements to gridpane
        ImageView homebox = new ImageView();
        this.gamegrid.add(homebox, j, i);
        return homebox;
    }
    
    public void lose(int player) {
        gamegridInfo.getStyleClass().clear();
        gamegridInfo.getStyleClass().add("lose");
        infoTopic.getStyleClass().clear();
        infoTopic.getStyleClass().add("fancy2");
        String topic = "JUMISSA!";
        String description = "Laatikkoa ei voi työntää maaliin.";
        setInfo(topic, description);
    }
    
    public void win(int player) {
        gamegridInfo.getStyleClass().clear();
        gamegridInfo.getStyleClass().add("win");
        infoTopic.getStyleClass().clear();
        infoTopic.getStyleClass().add("fancy1");
        String topic = "MAALISSA!";
        User winner = null;
        if (player == 1) {
            winner = GIC.getUser1();
        }
        if (player == 2) {
            winner = GIC.getUser2();
        }
        String description = "Onnea "+winner.getUsername()+"! Sait laatikon maaliin.";
        setInfo(topic, description);
    }
    
    public void setInfo(String topic, String description) {
        infoTopic.setText(topic);
        infoDescription.setText(description);
        gamegridInfo.setVisible(true);
        gamegrid.setOpacity(.6);
    }
    
    
    public void loadGVC(GameViewController GVC) {                               //Get supercontroller (gameview)
        this.GVC = GVC;
    }
    
    public void loadGIC(GameInfoController GIC) {                               //Get sibling controller (gameinfo)
        this.GIC = GIC;
    }
}
