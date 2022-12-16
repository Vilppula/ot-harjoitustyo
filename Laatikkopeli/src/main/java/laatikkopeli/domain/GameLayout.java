package laatikkopeli.domain;

public class GameLayout {
    
    private int areaId;
    private String name;
    private int height, width;
    private String[] layout;
    
    public GameLayout(int areaId, String name, String[] layout) {
        this.areaId = areaId;
        this.name = name;
        this.height = layout.length;
        this.width = layout[0].length();
        this.layout = layout;
    }

    public int getAreaId() {
        return areaId;
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
