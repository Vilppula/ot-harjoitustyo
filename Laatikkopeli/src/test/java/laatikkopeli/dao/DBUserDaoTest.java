package laatikkopeli.dao;

import java.sql.SQLException;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author lasse
 */
public class DBUserDaoTest {

    DBhandler DBH = new DBhandler("testDB");
    DBUserDao DBUD;
    User testUser = new User("test", "subject", "path");
    
    @Before
    public void setUpClass() {
        DBUD = new DBUserDao(DBH);
    }
    
    @Test
    public void newDBUserDaoIsCreated() {
        assertEquals(DBUserDao.class, DBUD.getClass());
    }
    
    @Test
    public void newUserCanBeAddedToDAO() {
        DBUD.saveUser(testUser);
        assertEquals(true, DBUD.findUser(new User("test", "...", "...")).equals(testUser));
    }
    
    @Test
    public void sameUserAddedTwiceReturnsFalse() {
        assertEquals(true, DBUD.saveUser(testUser));
        assertEquals(false, DBUD.saveUser(testUser));
    }
    
    @Test
    public void searchingUserNotInDBOrCacheReturnsNull() {
        assertEquals(null, DBUD.findUser(new User("some user", "", "")));
    }
    
    @After
    public void deleteTestDB() throws SQLException {
        this.DBH.clearDB();
    }
}
