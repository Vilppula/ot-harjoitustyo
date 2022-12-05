package laatikkopeli.domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Tile { //Tile has knowledege only about homebox it resides in and the image content of itself

    private Image bottom;            //Bottom layer image
    private ImageView homebox;       //Imageview of this node
    private Actor actor;             //Actor inside this tile
    
    
    public Tile(Image bottom, ImageView homebox, Actor actor) {                 //Constructor to init tile with bottom layer image
        this.homebox = homebox;
        this.bottom = bottom;
        this.actor = actor;
        updateTile();
    }

    public void setActor(Actor actor) {
        this.actor = actor;
        updateTile();
    }

    public Actor getActor() {
        return actor;
    }
    
    public void updateTile() {                                                  //Set current layer image visible in homebox
        if (this.actor == null)
            this.homebox.setImage(bottom);
        else {
            this.homebox.setImage(this.actor.getImage());              
        }   
    }
}
