package laatikkopeli.domain;

import java.util.Random;
import javafx.scene.image.Image;

//Helper to choose corresponding image for Character
public class ImagePicker {

    private String path = "/images/";
    
    public Image pick(char c) {                                            
        switch (c) {
            case ',': 
                return new Image(path + "blank.png");
            case '#':
                Random rnd = new Random();
                int n = rnd.nextInt(4);
                return new Image(path + "wall" + n + ".png");
            case '1':
                return new Image(path + "character1.png");
            case '2':
                return new Image(path + "character2.png");
            case '3':
                return new Image(path + "box1.png");
            case '4':
                return new Image(path + "box2.png");
            case '5':
                return new Image(path + "gate1.png");
            case '6':
                return new Image(path + "gate2.png");
        }
        return null;
    }
}
