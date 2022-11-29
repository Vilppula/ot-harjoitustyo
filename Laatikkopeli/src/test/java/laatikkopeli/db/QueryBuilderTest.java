package laatikkopeli.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderTest {
    
    QueryBuilder querybuilder;
    
    @Before
    public void serUp() {
        this.querybuilder = new QueryBuilder("users", "scores");
    }
    
    //========================================================================== create table sql
    @Test
    public void rigthCreateUsersTableSqlIsGenerated() {
        List<String> testList = new ArrayList<> (
                Arrays.asList("users","username","varchar(50)","password","varchar(50)")
        );
        String requiredResult = "CREATE TABLE IF NOT EXISTS users (\n"
                +"    username varchar(50),\n"
                +"    password varchar(50)\n"
                +")";
        
        assertEquals(requiredResult,this.querybuilder.newCreateTableQuery(testList));
    }
    
    @Test
    public void rigthCreateScoresTableSqlIsGenerated() {
        List<String> testList = new ArrayList<> (
                Arrays.asList("scores","username","varchar(50)","modeType","varchar(50)",
                        "levelID","integer","timestamp","timestamp","points","integer")
        );
        String requiredResult = "CREATE TABLE IF NOT EXISTS scores (\n"
                +"    username varchar(50),\n"
                +"    modeType varchar(50),\n"
                +"    levelID integer,\n"
                +"    timestamp timestamp,\n"
                +"    points integer\n"
                +")";
        
        assertEquals(requiredResult,this.querybuilder.newCreateTableQuery(testList));
    }
    //========================================================================== insert sqls
    @Test
    public void rightInsertUserSqlIsGenerated() {
        String requiredResult = "INSERT INTO users (username, password)\n"
                +"    VALUES ('name', 'pass')";
        assertEquals(requiredResult,this.querybuilder.newInsertQuery(new User("name","pass")));
    }
    
    @Test
    public void rightInsertScoreSqlIsGenerated() {
        Long datetime = System.currentTimeMillis();
        Timestamp stamp = new Timestamp(datetime);
        String requiredResult = "INSERT INTO scores (username, modeType, levelID, timestamp, points)\n"
                +"    VALUES ('user', 'singleplayer', 1, "+stamp.toString()+", 5000)";
        assertEquals(requiredResult,this.querybuilder.newInsertQuery(new Score("user","singleplayer", 1, stamp, 5000)));
    }
    
    //========================================================================== select sqls
    @Test
    public void rightSelectUserSqlIsGenerated() {
        String requiredResult = "SELECT * FROM users WHERE username='antero'";
        assertEquals(requiredResult,this.querybuilder.newSelectUserQuery("antero"));
    }
    
    @Test
    public void rightSelectLevelScoresSqlIsGenerated() {
        String requiredResult = "SELECT * FROM scores WHERE levelID=10";
        assertEquals(requiredResult,this.querybuilder.newSelectScoreQuery(10));
    }
}
