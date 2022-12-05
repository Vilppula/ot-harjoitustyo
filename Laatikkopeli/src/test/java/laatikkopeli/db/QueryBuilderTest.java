package laatikkopeli.db;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                Arrays.asList("users","username","varchar(50)","password","varchar(50)","avURL","varchar(200)")
        );
        String requiredResult = "CREATE TABLE IF NOT EXISTS users (\n"
                +"    username varchar(50),\n"
                +"    password varchar(50),\n"
                +"    avURL varchar(200)\n"
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
        String requiredResult = "INSERT INTO users (username, password, avURL)\n"
                +"    VALUES ('name', 'pass', 'path')";
        assertEquals(requiredResult,this.querybuilder.newInsertQuery(new User("name","pass","path")));
    }
    
    @Test
    public void rightInsertScoreSqlIsGenerated() {
        LocalDateTime time = LocalDateTime.now();
        String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time);
        String requiredResult = "INSERT INTO scores (username, modeType, levelID, datetime, points)\n"
                +"    VALUES ('user', 'singleplayer', 1, '"+datetime+"', 5000)";
        assertEquals(requiredResult,this.querybuilder.newInsertQuery(new Score("user","singleplayer", 1, datetime, 5000)));
    }
    
    //========================================================================== select sqls
    @Test
    public void correctSelectUserSqlIsGenerated() {
        String requiredResult = "SELECT * FROM users WHERE username='antero'";
        assertEquals(requiredResult,this.querybuilder.newSelectUserQuery("antero"));
    }
    
    @Test
    public void correctSelectAllUsersSqlIsGenerated() {
        String requiredResult = "SELECT * FROM users";
        assertEquals(requiredResult,this.querybuilder.newSelectUserQuery());
    }
    
    @Test
    public void correctSelectLevelScoresSqlIsGenerated() {
        String requiredResult = "SELECT * FROM scores WHERE levelID=10";
        assertEquals(requiredResult,this.querybuilder.newSelectScoreQuery(10));
    }
    
    @Test
    public void correctSelectAllScoresSqlIsGenerated() {
        String requiredResult = "SELECT * FROM scores";
        assertEquals(requiredResult,this.querybuilder.newSelectScoreQuery());
    }
}
