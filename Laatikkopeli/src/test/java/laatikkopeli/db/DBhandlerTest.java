package laatikkopeli.db;

import java.sql.SQLException;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class DBhandlerTest {
    
    DBhandler dbhandler;
    
    @Before
    public void setUp() {
        this.dbhandler = new DBhandler("testDB");
        this.dbhandler.initDBTables();
    }
    
    
    @Test
    public void newUserIsSavedToDB() throws SQLException {
        DBobject user = new User("test","subject");
        assertEquals(true, this.dbhandler.newRecord(user));
        DBobject newUser = this.dbhandler.getRecord("test");
        assertEquals(user, newUser);
    }
    
   
   
    @After
    public void endTesting() throws SQLException {
        this.dbhandler.clearDB();
    }
}
