package laatikkopeli.domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import laatikkopeli.controllers.GameInfoController;
import laatikkopeli.controllers.GameViewController;
import laatikkopeli.controllers.GamegridController;

//This class represents a traverse of one game
public class GameRunner {

    private GameViewController gvc;
    private GamegridController ggc;
    private GameInfoController gic;
    private ImagePicker images;                 //Imageservice
    private Tile[][] state;
    private int areaSize;
    private boolean twoPlayers = false;         //1 player by default
    private Actor[] players = new Actor[3];
    private int turn = 1;                       //Which player has a turn
    
    
    //New gamerunner ===========================================================
    public GameRunner(GamegridController ggc, GameInfoController gic,           //Create initial layout in constuctor (should move to own method?)
            GameViewController gvc) {       
        
        this.images = new ImagePicker();                                        //New image service
        this.ggc = ggc;                                                         //Connect to controllers (super: GVC, sub: GIC, GGC)
        this.gic = gic; 
        this.gvc = gvc;                         
    }
    
    
    //CREATE GAME AREA =========================================================
    public void createGameArea(GameLayout gameLayout) {
        this.areaSize = gameLayout.getSize();                                   //Get area size from layout-object
        this.state = new Tile[areaSize][areaSize];
        String layout = gameLayout.getLayout();                                 //String representing the layout
        for (int i = 0; i < this.areaSize; i++) {                               //Go through layout, char by char
            for (int j = 0; j < this.areaSize; j++) {
                
                char c = layout.charAt(this.areaSize * i + j);                      //Get char from String 'layout'
                Actor actor = new Actor(i, j, c, images.pick(c));
                if (c == '1') {
                    players[1] = actor;
                }
                if (c == '2') {
                    players[2] = actor;
                }
                ImageView homebox = this.ggc.addToGrid(i, j);                   //Ask homebox (ImageView) from gamegrid controller by coordinate
                Tile newTile = createTile(homebox, c, actor);                   //Create tile and give it it's homebox as reference
                state[i][j] = newTile;                                          //References to grphical elements
            }
        }
    }
    
    
    //DESIGNATE NEW TILE TO GRID ===============================================
    public Tile createTile(ImageView homebox, char c, Actor actor) {
        Image floor = images.pick(',');
        if (c == ',') {                                                         //Create empty floor
            return new Tile(floor, homebox, null);
        }                    
        return new Tile(floor, homebox, actor);                                 //Otherwise create something else
    }
    
    
    //KEY PRESSES ==============================================================
    public void checkKey(KeyCode code) {
        String key = code.getName();
        Actor player = players[turn];
        if (key.equals("Up")) {                                                 
            move(player, -1, 0);                                                //Ask 'move'-method to change position of a character (instance of actor)
        } else if (key.equals("Down")) {
            move(player, 1, 0);
        } else if (key.equals("Left")) {
            move(player, 0, -1);
        } else if (key.equals("Right")) {
            move(player, 0, 1);
        }
        if (twoPlayers) {                                                       //Switch turn if two player game
            turn = -(turn - 3);
        }
    }
    
    
    // MOVE ACTORS =============================================================
    public boolean move(Actor actor, int translateI, int translateJ) {          //Try to move actor (recursion possible if actor tries to move another actor)
        int oldI = actor.getI(), oldJ = actor.getJ();                           //Ask actor for it's location
        int newI = oldI + translateI, newJ = oldJ + translateJ;                     //Calculate location actor is trying to move into
        System.out.println("Try to move actor '" + actor.getRole() + "' from "
                + "(" + oldJ + "," + oldI + ") to (" + newJ + "," + newI + ")");
        if (!checkMove(newI, newJ, translateI, translateJ)) {                   //Check if move is allowed ...
            return false;
        }          
        state[newI][newJ].setActor(actor);                                      //Update tiles...
        state[oldI][oldJ].setActor(null);
        actor.setI(newI); 
        actor.setJ(newJ);
        return true;
    }
    
            
    //CHECK LEGIT MOVES FOR A CHAR/BOX =========================================
    public boolean checkMove(int i, int j, int translateI, int translateJ) {    //Confirm allowed move here
        if (i < 0 || j < 0 || i > areaSize - 1 || j > areaSize - 1) {           //Outside gamearea
            return false;
        }   
        Actor nextActor = state[i][j].getActor();
        if (nextActor == null) {                                                //No actor in next tile
            return true;
        }                                         
        char role = nextActor.getRole();
        if (role == '3' || role == '4') {
            return move(nextActor, translateI, translateJ);
        }
        return false;
    }
}
