package laatikkopeli.domain;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TileTest {
    
    //iStream used to mockup Image (javaFX graphical element)
    InputStream iStream = new BufferedInputStream(new ByteArrayInputStream("111".getBytes()));
    InputStream iStream2 = new BufferedInputStream(new ByteArrayInputStream("222".getBytes()));
    Image actorImage = new Image(iStream);
    Image bottomImage = new Image(iStream2);
    Actor actor = new Actor(1,1,'1',actorImage);
    ImageView homebox;
    
    @Before
    public void setUp() {
        homebox = new ImageView();
    }

    @Test
    public void newTileIsCreatedWithActorOnIt() {
        Tile tile = new Tile(bottomImage, homebox, actor);
        assertEquals(actorImage, homebox.getImage());
    }
    
    @Test
    public void newEmptyTileIsCreatedIfNoActorGivenAsArgument() {
        Tile tile = new Tile(bottomImage, homebox, null);
        assertEquals(bottomImage, homebox.getImage());
    }
    
    @Test
    public void actorImageIsUpdatedToTileIfActorMovesIn() {
        Tile tile = new Tile(bottomImage, homebox, null);
        assertEquals(bottomImage, homebox.getImage());
        tile.setActor(actor);
        assertEquals(actorImage, homebox.getImage());
    }
    
    @Test
    public void actorIsRemovedAndTileHasBottomImage() {
        Tile tile = new Tile(bottomImage, homebox, actor);
        assertEquals(actorImage, homebox.getImage());
        tile.setActor(null);
        assertEquals(bottomImage, homebox.getImage());
    }
    
    
}
