package laatikkopeli.db;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class DBhandlerTest {
    
    DBhandler dbhandler;
    User testUser = new User("test","subject","path");
    
    @Before
    public void setUp() {
        this.dbhandler = new DBhandler("testDB");
        this.dbhandler.initDBTables();
    }
    
    @Test
    public void newUserIsSavedToDB() throws SQLException {
        DBobject user = testUser;
        assertEquals(true, this.dbhandler.newRecord(user));
        DBobject newUser = this.dbhandler.getRecord("test");
        assertEquals(user, newUser);
    }
    
    @Test
    public void newScoreIsSavedToDB() throws SQLException {
        LocalDateTime time = LocalDateTime.now();
        String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time);
        DBobject testScore = new Score("user","mode",1,datetime,1000);
        assertEquals(true, this.dbhandler.newRecord(testScore));
        List<DBobject> returnScores = this.dbhandler.getScores(1);
        assertEquals(true, returnScores.contains(testScore));
    }
   
    @After
    public void endTesting() throws SQLException {
        this.dbhandler.clearDB();
    }
}
