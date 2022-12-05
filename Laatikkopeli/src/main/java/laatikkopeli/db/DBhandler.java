package laatikkopeli.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;


public class DBhandler {                    //Sqlite database utility class
    
    private String DBname;
    private QueryBuilder queryBuilder;
    private Connection connection;
    private String query;
    private String usersTable = "users";
    private String scoresTable = "scores";
    
    
    // Constructor
    public DBhandler(String DBname){        //DB connection upon creation of instance
        this.DBname=DBname;
        this.queryBuilder = new QueryBuilder(this.usersTable,this.scoresTable);
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+this.DBname+".db");
            initDBTables();
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //========================================================================== Insert new record to database
    public boolean newRecord(DBobject dbo) {                                    
        if (dbo.getClass() == User.class)
            this.query = this.queryBuilder.newInsertQuery((User) dbo);
        else if (dbo.getClass() == Score.class)
            this.query = this.queryBuilder.newInsertQuery((Score) dbo);
        return insert();
    }

    //========================================================================== Get single record, return as DBobject
    
    public DBobject getRecord(String username) throws SQLException {            //Get user-type record
        this.query = this.queryBuilder.newSelectUserQuery(username);
        ResultSet results = select();
        if (results == null) return null;
        return new User(results.getString(1), results.getString(2), results.getString(3));
    }
    
    //========================================================================== Get multiple records, return as List<DBobject>
    
    public List<Score> getRecords(int levelId) throws SQLException {         //Get scores by levelId
        this.query = this.queryBuilder.newSelectScoreQuery(levelId);
        ResultSet results = select();
        if (results == null) return null;
        List<Score> scores = new ArrayList<>();
        while (results.next()) {
            scores.add(new Score(results.getString(1), results.getString(2), results.getInt(3), 
                    results.getString(4), results.getInt(5)));
        }
        return scores;
    }
    
    //========================================================================== Get all records by tablename, return as list of DBobjects
    public ResultSet getAll(String tablename) {                                 
        if (tablename.equals(this.usersTable))
            this.query = this.queryBuilder.newSelectUserQuery();
        else if (tablename.equals(this.scoresTable))
            this.query = this.queryBuilder.newSelectScoreQuery();
        ResultSet results = select();
        return results;    
    }
    //========================================================================== Check that correct tables can be found from database
    public boolean initDBTables() {
        try {
            //If correct tables are not present the they will be created
            Statement statement = this.connection.createStatement();
            List<String> tablelist = new ArrayList<>(Arrays.asList(
                    "users",
                    "username","varchar(50)",
                    "password","varchar(50)",
                    "avURL","varchar(200)"
            ));
            this.query = this.queryBuilder.newCreateTableQuery(tablelist);
            System.out.println("KYSELY: "+this.query);
            statement.execute(query);
            tablelist = new ArrayList<>(Arrays.asList(
                    "scores",
                    "username","varchar(50)",
                    "modeType","varchar(50)",
                    "levelID","integer",
                    "datetime","varchar(50)",
                    "points","integer"
            ));
            this.query = this.queryBuilder.newCreateTableQuery(tablelist);
            System.out.println("KYSELY: "+this.query);
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //========================================================================== Execute insert and notify
    private boolean insert() {                       
        System.out.println("KYSELY: "+this.query);
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.execute(this.query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //========================================================================== Execute select and return resultSet
    private ResultSet select() {         
        System.out.println("KYSELY: "+this.query);
        Statement statement; ResultSet results;
        try {
            statement = this.connection.createStatement();
            results = statement.executeQuery(this.query);
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //========================================================================== Execute update and notify
    private boolean update() {
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeQuery(this.query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //========================================================================== Clear database (used to delete testDB)
    public boolean clearDB() throws SQLException {
        this.connection.close();
        File dbFile = new File(this.DBname+".db");
        dbFile.delete();
        return true;
    }

}
