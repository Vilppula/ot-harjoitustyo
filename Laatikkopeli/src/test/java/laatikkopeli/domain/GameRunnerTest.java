package laatikkopeli.domain;

import laatikkopeli.controllers.GameInfoController;
import laatikkopeli.controllers.GameViewController;
import laatikkopeli.controllers.GamegridController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GameRunnerTest {
    
    GameRunner gameRunner;
    String[] layoutTest = {
        "1,,",
        ",,,"    
    };
    GameLayout testGameLayout = new GameLayout(1,"test", layoutTest);
    
    @Before
    public void setUp() {
        gameRunner = new GameRunner(new GamegridController(), new GameInfoController(), new GameViewController());
    }
    
    
    @Test
    public void gameRunnerInstanceIsCreated() {
        assertEquals(this.gameRunner.getClass(), GameRunner.class);
    }
    
    @Test
    public void gameHasOnePlayerWhenNewGameCanBeStarted() {
    }
    
    @Test
    public void createTileMethodCanCreateFloorTiles() {
       
    }
    
    @Test
    public void createTileMethodCanCreateWallTiles() {
        
    }
    
    @Test
    public void playerCannotMoveOutsideGameArea() {
       assertEquals(false, this.gameRunner.checkMove(0, 0, -1, 0));
       assertEquals(false, this.gameRunner.checkMove(0, 0, 0, -1));
    }
}
