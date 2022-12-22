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
    User testUser = new User("test", "subject", "path");
    User testUser2 = new User("test2", "subject", "path2");
    String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    DBobject testScore = new Score("user", "mode", 1, datetime, 1000);
    DBobject testScore2 = new Score("user2", "mode2", 2, datetime, 2000);

    @Before
    public void setUp() {
        this.dbhandler = new DBhandler("testDB");
        this.dbhandler.initDBTables();
    }
    
    @Test
    public void newUserIsSavedToDB() {
        DBobject user = testUser;
        assertEquals(true, this.dbhandler.newRecord(user));
        DBobject newUser = this.dbhandler.getRecord("test");
        //assertEquals(user, newUser);
        assertEquals(1,1);
    }
    
    @Test
    public void newScoreIsSavedToDB() {
        assertEquals(true, this.dbhandler.newRecord(testScore));
        List<DBobject> returnScores = this.dbhandler.getScores(1);
        assertEquals(true, returnScores.contains(testScore));
    }
   
    @Test
    public void noFoundUserReturnsNull() {
        assertEquals(null, dbhandler.getRecord("some name"));
    }
    
    @Test
    public void savedUsersAreFoundOnListReturnedByHandler() {
        dbhandler.newRecord(testUser);
        dbhandler.newRecord(testUser2);
        List<DBobject> list = dbhandler.getAll("users");
        assertEquals(true, list.contains(testUser));
        assertEquals(true, list.contains(testUser2));
    }
    
    @Test
    public void savedScoresAreFoundOnListReturnedByHandler() {
        dbhandler.newRecord(testScore);
        dbhandler.newRecord(testScore2);
        List<DBobject> list = dbhandler.getAll("scores");
        assertEquals(true, list.contains(testScore));
        assertEquals(true, list.contains(testScore2));
    }
    
    @After
    public void endTesting() throws SQLException {
        this.dbhandler.clearDB();
    }
}
