package laatikkopeli.domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {

    private double x;
    private double y;
    private ImageView imageView;
    
    public Tile(double x, double y, Image image) {
        this.x = x*20.0; this.y = y*20.0;
        this.imageView = new ImageView(image);
        this.imageView.setX(this.x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setImageView(Image image) {
        this.imageView.setImage(image);
    }

    public ImageView getImageView() {
        return this.imageView;
    }


}
