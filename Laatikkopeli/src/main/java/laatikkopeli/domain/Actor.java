package laatikkopeli.domain;

import javafx.scene.image.Image;


public class Actor {

    private int i, j;
    private char role;
    private Image image;

    public Actor(int i, int j, char role, Image image) {
        this.i = i;
        this.j = j;
        this.role = role;
        this.image = image;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public char getRole() {
        return role;
    }

    public Image getImage() {
        return image;
    }
}
