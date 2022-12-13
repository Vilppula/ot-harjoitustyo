package laatikkopeli.domain;

public class GameLayout {
    
    private String name;
    private int height, width;
    private String[] layout;
    
    public GameLayout(String name, String[] layout) {
        this.name = name;
        this.height = layout.length;
        this.width = layout[0].length();
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public String[] getLayout() {
        return layout;
    }
}
