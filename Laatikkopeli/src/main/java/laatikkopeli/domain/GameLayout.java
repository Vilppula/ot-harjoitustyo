package laatikkopeli.domain;

public class GameLayout {
    
    private String name;
    private int size;
    private String layout;

    public GameLayout(String name, int size, String layout) {
        this.name = name;
        this.size = size;
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
    
    public String getLayout() {
        return layout;
    }
}
