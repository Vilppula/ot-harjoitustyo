package laatikkopeli.dao;

import java.util.ArrayList;
import java.util.List;
import laatikkopeli.domain.GameLayout;

//This service is only for testing
public class GameAreaDao {

    private List<GameLayout> areas;
    private String fileName;
    
    String[] testLayout1 = {                
        ",,,,,,,,,,,,,,,"
        , ",,,,,,#,#,,,,5,"
        , ",,#####,#####,,"
        , ",,#,,,#,#,,,#,,"
        , ",,#,,,#,#,,,#,,"
        , ",,#####,#####,,"
        , ",,,,,,,,,,,,,,#"
        , ",,#####,#####,,"
        , ",,#####,######,"
        , ",,,,,,,3,,,,,,,"
        , ",,#####,#####,,"
        , ",,#,,,#,#,,,#,,"
        , ",,#####,#####,,"
        , ",1,,,,,,,,,,,,,"
        , ",,,,,,,,,,,,,,,"
    };
    
    private GameLayout level = new GameLayout(1, "testlevel", testLayout1);         //Use this until game chooser have been built
    
    String[] testLayout2 = {
        ",,,#,,,,,,",
        ",1,#,#,#3,",
        ",,,#,#,,,,",
        ",###,#,###",
        ",,,#,#,5,,",
        ",,,,,,,,,,",
        ",,,#,##,,#"
    };
    
    private GameLayout level2 = new GameLayout(2, "testlevel2", testLayout2);
    
    public GameAreaDao() {
        this.areas = new ArrayList<>();
        this.areas.add(level);
        this.areas.add(level2);
    }
    
    public List<GameLayout> getAll() {
        return this.areas;
    }
    
    public List<GameLayout> getSinglePlayerAreas() {
        return null;
    }
    
    public List<GameLayout> getTwoPlayerAreas() {
        return null;
    }
}
