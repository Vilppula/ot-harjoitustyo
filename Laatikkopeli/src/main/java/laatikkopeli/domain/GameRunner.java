package laatikkopeli.domain;

import javafx.scene.input.KeyCode;
import laatikkopeli.algorithm.SPAlgorithm;
import laatikkopeli.controllers.GameInfoController;
import laatikkopeli.controllers.GameViewController;
import laatikkopeli.controllers.GamegridController;
/**
 * Class representing the traverse of a single gameplay
 * 
 * @author lasse
 */
public class GameRunner {

    private GameViewController gvc;             //Connection to FXML controllers
    private GamegridController ggc;
    private GameInfoController gic;
    private SPAlgorithm spa;                    //Algorithm used to calculate shortest possible path to finish (see TiRa 2)
    private Tile[][] tiles;
    private char[][] charState;
    private int width, height;
    private boolean twoPlayers = false;         //1 player by default
    private Actor[] players = new Actor[3];     //Keep references to players
    private Actor[] gates = new Actor[3];       //References to gates
    private int turn = 1;                       //Which player has a turn
    private int[] steps = new int[3];           //Count player steps here

    /**
     * New gamerunner instance. Game logic component for laatikkopeli
     * 
     * @param ggc
     * @param gic
     * @param gvc 
     */
    public GameRunner(GamegridController ggc, GameInfoController gic,           //Create initial layout in constuctor (should move to own method?)
            GameViewController gvc) {       
        this.ggc = ggc;                                                         //Connect to controllers (super: GVC, sub: GIC, GGC)
        this.gic = gic; 
        this.gvc = gvc;
    }
    
    /**
     * Used to call gamerunner to start new game (area, initial values, fx-elements)
     * 
     * @param gameLayout 
     */
    public void startGame(GameLayout gameLayout) {                              //Initialize new game here
        GameAreaBuilder gab = new GameAreaBuilder();                            //Build Layout (String[]) to game. Take notes of all required attributes (see below)
        gab.createGameArea(gameLayout, ggc);                                    //Ask game area builder to construct game from game layout
        this.tiles = gab.getTiles();
        this.height = gab.getHeight();
        this.width = gab.getWidth();
        this.charState = gab.getCharState();                                    //Charstate is used to inform SPAlgorithm about momentary situation in game
        this.players = new Actor[]{null, gab.getP1(), gab.getP2()};
        this.gates = new Actor[]{null, gab.getG1(), gab.getG2()};
        if (players[2] != null) twoPlayers = true;
        this.spa = new SPAlgorithm(charState, width, height);
        updateSPA();
    }
    
    /**
     * Handle keypresses in this method
     * 
     * @param code 
     */
    public void checkKey(KeyCode code) {                                        //Check keyboard code
        String key = code.getName();
        Actor player = players[turn];                                           //Choose player in turn from players[null, player1, player2]
        boolean moved = false;
        if (key.equals("Up")) {                                                 
            moved = move(player, -1, 0);                                        //Ask 'move'-method to change position of a character (instance of actor)
        } else if (key.equals("Down")) {
            moved = move(player, 1, 0);
        } else if (key.equals("Left")) {
            moved = move(player, 0, -1);
        } else if (key.equals("Right")) {
            moved = move(player, 0, 1);
        }
        if (moved) updateSPA();                                                 //If player was moved, calculate new shortest path
        if (twoPlayers) {                                                       //Switch turn if two player game
            turn = -(turn - 3);                                                 // 1->2, 2->1
            this.gic.switchTurn();
        }
    }
    /**
     * Method used to move actors (moveble).
     * 
     * @param actor
     * @param translateI
     * @param translateJ
     * @return 
     */
    public boolean move(Actor actor, int translateI, int translateJ) {          //Try to move actor (recursion possible if actor tries to move another actor)
        int oldI = actor.getI(), oldJ = actor.getJ();                           //Ask actor for it's current location
        int newI = oldI + translateI, newJ = oldJ + translateJ;                 //Calculate location which actor is trying to move into
        if (!checkMove(newI, newJ, translateI, translateJ)) {                   //Check if move is allowed ...
            return false;
        }
        tiles[newI][newJ].setActor(actor);                                      //Update tiles...
        tiles[oldI][oldJ].setActor(null);
        charState[newI][newJ] = actor.getRole();                                //Update charstate (for SPA)
        charState[oldI][oldJ] = ',';
        actor.setI(newI);                                                       //Update actor's location
        actor.setJ(newJ);
        if (actor.getRole() == '1') this.gic.updateP1Counter();                                           
        if (actor.getRole() == '2') this.gic.updateP1Counter();                                           
        return true;
    }
    
            
    /**
     * Check allowed moves of actors (moveable)
     * Shortest path algorithm is called after this (if required)
     * 
     * @param i
     * @param j
     * @param translateI
     * @param translateJ
     * @return 
     */
    public boolean checkMove(int i, int j, int translateI, int translateJ) {    // Check if move is allowed
        if (i < 0 || j < 0 || i > height - 1 || j > width - 1) {                // If move is directed out of the game area
            return false;
        }   
        Actor nextActor = tiles[i][j].getActor();
        if (nextActor == null) {                                                // No actor in next tile
            return true;
        }                                         
        char role = nextActor.getRole();                                        
        if (role == '3' || role == '4') {                                       //If there is a box in next square, use move-method to given box
            return move(nextActor, translateI, translateJ);
        }
        return false;
    }
    
    /**
     * Call for shortest path algorithm to check the current game state
     */
    private void updateSPA() {
        int newMin = spa.count(charState, gates[turn].getI(), 
                gates[turn].getJ());                                            //Use algorithm to calculate shortest path after actors have moved
        if (newMin == -1) {
            ggc.lose(turn);
        }
        if (newMin == 0) {
            ggc.win(turn);
            return;
        }
        this.gic.setPlayer1Min(newMin);                                         //Display new minimum distance to goal
    }
}
