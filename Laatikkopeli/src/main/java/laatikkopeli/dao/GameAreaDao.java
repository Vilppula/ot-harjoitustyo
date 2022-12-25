package laatikkopeli.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import laatikkopeli.domain.GameLayout;


public class GameAreaDao {

    private List<GameLayout> singlePlayerAreas;    
    private List<GameLayout> twoPlayerAreas;

    public GameAreaDao() {
        this.singlePlayerAreas = new ArrayList<>();
        this.twoPlayerAreas = new ArrayList<>();
        String location = new File("").getAbsolutePath() + "/src/main/resources/areas";
        readFiles(location);
    }
    
    /**
     * Read files on given location. Read content to be passed on to parseContent 
     * @param path 
     */
    public void readFiles(String path) {
        File folder = new File(path);
        for (File file: folder.listFiles()) {
            if (file.getName().split("\\.")[1].equals("ll")) {
                String content = "";
                try {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {
                        content += scan.nextLine() + ">";
                    }
                } catch (FileNotFoundException ex) {
                    continue;
                }
                parseContent(content);
            }
        }
    }
    
    /**
     * Read filecontent and construct GameLayout. Will be saved to corresponding List<GameLayout>
     * @param content 
     */
    public void parseContent(String content) {
        GameLayout gameLayout;
        String[] meta = content.split(">");
        String[] layout = new String[meta.length - 1];                            
        for (int i = 1; i < meta.length; i++) {
            layout[i - 1] = meta[i];
        }
        String[] info = meta[0].split("#");                                     //First line of file is formed: id#name#NumOSfPlayers
        gameLayout = new GameLayout(
                Integer.valueOf(info[0]), info[1], layout);
        if (info[2].equals("1")) {
            this.singlePlayerAreas.add(gameLayout);
        } else if (info[2].equals("2")) {
            this.twoPlayerAreas.add(gameLayout);
        }
    }
    
    public List<GameLayout> getSinglePlayerAreas() {
        return this.singlePlayerAreas;
    }
    
    public List<GameLayout> getTwoPlayerAreas() {
        return this.twoPlayerAreas;
    }
}
