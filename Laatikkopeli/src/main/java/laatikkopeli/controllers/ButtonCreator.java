package laatikkopeli.controllers;

import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import laatikkopeli.domain.GameLayout;


public class ButtonCreator {
    
    private int height, width, scale;
    Map<Character, String> colors = Map.of(                                     //Define colors fot mini-image
            '1', "#b6882a",
            '2', "#d3373b",
            '3', "#b6882a",
            '4', "#d3373b",
            '5', "#b6882a",
            '6', "#d3373b",
            '#', "#9d3d26",
            ',', "#000000"
    );

    public ButtonCreator(int height, int width, int scale) {
        this.height = height;
        this.width = width;
        this.scale = scale;
    }
    
    public HBox create(GameLayout gameLayout) {
        HBox box = new HBox();
        box.setStyle("-fx-background-color:transparent");
        Button button = new Button();
        button.setGraphic(new ImageView(makeMinimap(gameLayout)));
        box.getChildren().add(button);
        return box;
    }
    
    private Image makeMinimap(GameLayout gameLayout) {
        WritableImage img = new WritableImage(gameLayout.getWidth()*scale,gameLayout.getHeight()*scale);    
        String[] layout = gameLayout.getLayout();
        PixelWriter pix = img.getPixelWriter();
        for (int i = 0; i < gameLayout.getHeight()*scale; i++) {
            for (int j = 0; j < gameLayout.getWidth()*scale; j++) {
                String color = colors.get(layout[i/scale].charAt(j/scale));
                pix.setColor(j, i, Color.web(color));
            }
        }
        return img;
    }
}
