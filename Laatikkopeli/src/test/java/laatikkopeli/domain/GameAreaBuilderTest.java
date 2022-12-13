package laatikkopeli.domain;

import laatikkopeli.controllers.GameInfoController;
import laatikkopeli.controllers.GameViewController;
import laatikkopeli.controllers.GamegridController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GameAreaBuilderTest {
    
    GameAreaBuilder builder;
    String[] layoutTest = {
        "1,,",
        ",,,"    
    };
    GameLayout testGameLayout = new GameLayout("test", layoutTest);
    
    @Before
    public void setUp() {
            builder = new GameAreaBuilder();
    }

    @Test
    public void tilesElementIsPresentWhennewAreaIsBuild() {
    }
}