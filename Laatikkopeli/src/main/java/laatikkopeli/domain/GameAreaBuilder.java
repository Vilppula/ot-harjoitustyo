package laatikkopeli.domain;

import javafx.scene.image.ImageView;
import laatikkopeli.controllers.GamegridController;
/**
 * Class is used to build gamearea from instance of GameLayout-class
 */
public class GameAreaBuilder {
    
    private int width;
    private int height;
    private char[][] charState;
    private Tile[][] tiles;
    private ImagePicker images;
    private Actor P1, P2, G1, G2;
    
    /**
     * Initialize new gamearea and found required attributes (coordinates etc.)
     */
    public void createGameArea(GameLayout gameLayout, GamegridController ggc) {

        this.images = new ImagePicker();                                        //Image service to choose corresponding image for a character (or role)
        this.height = gameLayout.getHeight();
        this.width = gameLayout.getWidth();                                     //Get area size from layout-object
        this.charState = new char[height][width];                               //This depiction of state will be used with shortest path algorithm (class SPAlgorithm)
        this.tiles = new Tile[height][width];                                   //Reference to tile-objects. Connected to GridPane in gamegrid.fxml
        String[] layout = gameLayout.getLayout();
        System.out.println("Lets create gamearea: h:"+height+", w:"+width);
        for (int i = 0; i < height; i++) {                                      //Go through layout, char by char
            for (int j = 0; j < width; j++) {
                
                char c = layout[i].charAt(j);                                   //Get char from String 'layout'
                charState[i][j] = c;                                            
                Actor actor = new Actor(i, j, c, images.pick(c));               //New actor. (from tiles and gates also -> considered null when put in tile)
                if (c == '1') {
                    P1 = actor;
                } else if (c == '2') {
                    P2 = actor;
                } else if (c == '5') {
                    G1 = actor;
                } else if (c == '6') {
                    G2 = actor;
                };
                
                ImageView homebox = ggc.addToGrid(i, j);                        //Ask homebox (ImageView) from gamegrid controller by coordinate
                Tile newTile = createTile(homebox, c, actor);                   //Create tile and pass it's homebox as reference
                tiles[i][j] = newTile;                                          //References to grphical elements
            }
        }
    }
    
    /**
    * Helper method used to build tile instance. (Imageview, character, Actor)
    */
    private Tile createTile(ImageView homebox, char c, Actor actor) {
        if (c == ',' || c == '5' || c == '6') {                                 //Floow or gate are considered ass null-actors (can be stepped on. See class 'Tile')
            return new Tile(images.pick(c), homebox, null);
        }                    
        if (c == '1' || c == '2' || c == '3' || c == '4') {                     //Create tile with actor on it
            return new Tile(images.pick(','), homebox, actor);
        }
        return new Tile(images.pick(c), homebox, actor);                        //Otherwise create something else
    }

    public char[][] getCharState() {
        return charState;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Actor getP1() {
        return P1;
    }

    public Actor getP2() {
        return P2;
    }

    public Actor getG1() {
        return G1;
    }

    public Actor getG2() {
        return G2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
