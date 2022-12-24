
package laatikkopeli.dao;

import java.sql.SQLException;
import java.util.HashSet;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.GameLayout;
import laatikkopeli.domain.Score;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


/**
 * JUnit testing for DBUserDao class
 * @author lasse
 */
public class DBScoreDaoTest {
    
    DBhandler DBH = new DBhandler("testDB");
    DBScoreDao DBSD;
    GameLayout testArea = new GameLayout(1, "area", new String[]{",,,"});
    Score testScore = new Score("test", "mode", 1, "datetime", 1000);
    
    @Before
    public void setUpClass() {
        DBSD = new DBScoreDao(DBH);
    }
    
    @Test
    public void newDBScoreDaoIsCreated() {
        assertEquals(DBScoreDao.class, DBSD.getClass());
    }
    
    @Test
    public void newScoreCanBeSavedAndThenCanBeFoundByAreaId() {
        DBSD.addScore(testScore);
        assertEquals(true, DBSD.findByGameArea(testArea.getAreaId()).contains(testScore));
    }
    
    @Test
    public void newScoreCanBeSavedAndThenCanBeFoundByUsername() {
        DBSD.addScore(testScore);
        assertEquals(true, DBSD.findByUser(testScore.getUsername()).contains(testScore));
    }
    
    @Test
    public void newScoreHasNoDuplicatesOnList() {
        DBSD.addScore(testScore);
        DBSD.addScore(testScore);
        HashSet<Score> set = new HashSet<>();
        for (Score score: DBSD.findByGameArea(testScore.getAreaId())) {
            assertEquals(true, set.add(score));                                 //Set has no duplicates so result false will fail the test
        }
    }
    
    @After
    public void deleteTestDB() throws SQLException {
        this.DBH.clearDB();
    }
}
