package laatikkopeli.db;

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
    
    private QueryBuilder queryBuilder;
    private Connection connection;
    private String query;
    private String usersTable = "users";
    private String scoresTable = "scores";
    
    
    // Constructor
    public DBhandler(String DBname){        //DB connection upon creation of instance
        this.queryBuilder = new QueryBuilder(this.usersTable,this.scoresTable);
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+DBname+".db");
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
    public DBobject getRecord(String username) throws SQLException {            
        this.query = this.queryBuilder.newSelectUserQuery(username);
        ResultSet results = select();
        if (results == null) return null;
        return new User(results.getString(1), results.getString(2));
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
    private boolean initDBTables() {
        try {
            //If correct tables are not present the they will be created
            Statement statement = this.connection.createStatement();
            List<String> tablelist = new ArrayList<>(Arrays.asList(
                    "users","username","varchar(50)","password","varchar(50)"
            ));
            this.query = this.queryBuilder.newCreateTableQuery(tablelist);
            statement.execute(query);
            tablelist = new ArrayList<>(Arrays.asList(
                    "scores","username","varchar(50)","modeType","varchar(50)",
                    "levelID","integer","timestamp","timestamp","points","integer"
            ));
            this.query = this.queryBuilder.newCreateTableQuery(tablelist);
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
}
