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

/**
 * This class uses JDBC to access SQLite database
 * @author lasse
 */
public class DBhandler {                    //Sqlite database utility class
    
    private String dbName;
    private QueryBuilder queryBuilder;
    private Connection connection;
    private String query;
    
    //SQL tables are defined here
    List<String> userFields = new ArrayList<>(Arrays.asList(
                    "users",
                    "username", "varchar(50)",
                    "password", "varchar(50)",
                    "avURL", "varchar(200)"
            ));
    List<String> scoreFields = new ArrayList<>(Arrays.asList(
                    "scores",
                    "username", "varchar(50)",
                    "modeType", "varchar(50)",
                    "levelID", "integer",
                    "datetime", "varchar(50)",
                    "points", "integer",
                    "unique", "(username, levelID, datetime)"
            ));
    
    
    public DBhandler(String dbName) {        //DB connection upon creation of instance
        this.dbName = dbName;
        this.queryBuilder = new QueryBuilder(this.userFields.get(0), this.scoreFields.get(0));
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbName + ".db");
            initDBTables();
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //========================================================================== Check that correct tables can be found from database
    /**
     * Create tables when db is created
     * @return 
     */
    public boolean initDBTables() {
        try {
            Statement statement = this.connection.createStatement();            //If correct tables are not present the they will be created here
            this.query = this.queryBuilder.newCreateTableQuery(userFields);
            printQuery();
            statement.execute(query);
            this.query = this.queryBuilder.newCreateTableQuery(scoreFields);
            printQuery();
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //========================================================================== Insert new record or update
    /**
     * Create new User- or Score-record
     * @param dbo
     * @return 
     */
    public boolean newOrUpdate(DBobject dbo, boolean isNew) {                                    
        if (dbo.getClass() == User.class) {
            this.query = this.queryBuilder.newInsertQuery((User) dbo);
        } else if (dbo.getClass() == Score.class) {
            if (isNew) {
                this.query = this.queryBuilder.newInsertQuery((Score) dbo);
            } else {
                this.query = this.queryBuilder.newUpdateScoresQuery((Score) dbo);
            }
        }
        return insertOrUpdate();
    }

    //========================================================================== Get single record, return as DBobject
    /**
     * 
     * @param username
     * @return 
     */
    public DBobject getRecord(String username) {                                    //Get user-type record
        this.query = this.queryBuilder.newSelectUserQuery(username);
        ResultSet results = select();
        try {
            if (results.getRow() != 0) {
                return new User(results.getString(1), results.getString(2), results.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBhandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //========================================================================== Get multiple records, return as List<DBobject>
    /**
     * Get all scores related to one GameLayout (top 10 scores persist)
     * @param levelId
     * @return 
     */
    public List<DBobject> getScores(int levelId) {
        this.query = this.queryBuilder.newSelectScoreQuery(levelId);
        return collectToList(select());
    }
    
    /**
     * Get all scores related to one User
     * @param username
     * @return 
     */
    public List<DBobject> getScores(String username) {
        this.query = this.queryBuilder.newSelectScoreQuery(username);
        return collectToList(select());
    }
    
    //========================================================================== Get all records by tablename, return as list of DBobjects
    /**
     * Get all dbobjects from one table
     * @param tablename
     * @return 
     */
    public List<DBobject> getAll(String tablename) {                                 
        if (tablename.equals(userFields.get(0))) {
            this.query = this.queryBuilder.newSelectUserQuery();
        } else if (tablename.equals(scoreFields.get(0))) {
            this.query = this.queryBuilder.newSelectScoreQuery();
        }
        return collectToList(select());
    }
    
    //========================================================================== Execute insert and notify
    /**
     * Will be called after an insert-type query have been set by querybuilder.
     * @return 
     */
    private boolean insertOrUpdate() {                       
        printQuery();
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.execute(this.query);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    //========================================================================== Execute select and return resultSet
    /**
     * Will be called after a select-type query have been set by querybuilder.
     * @return 
     */
    private ResultSet select() {         
        printQuery();
        Statement statement;
        ResultSet results;
        try {
            statement = this.connection.createStatement();
            results = statement.executeQuery(this.query);
            return results;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    //========================================================================== ResultSet to List
    /**
     * Helper method to collect data from instance of JDBC ResultSet class
     * @param results
     * @return 
     */
    public List<DBobject> collectToList(ResultSet results) {                    //Handle ResultSets and exeptions here. Return list or null.
        List<DBobject> list = new ArrayList<>();
        String table;
        try {
            table = results.getMetaData().getTableName(1);
            while (results.next()) {
                if (table.equals("scores")) {
                    list.add(new Score(results.getString(1), results.getString(2),
                            results.getInt(3), results.getString(4), results.getInt(5)));
                } else if (table.equals("users")) {
                    list.add(new User(results.getString(1), results.getString(2),
                            results.getString(3)));
                }
            }
            return list;
            
        } catch (Exception ex) {
            return null;
        }
    }
    
    //========================================================================== Clear database (used to delete testDB)
    /**
     * This method deletes database file (used in testing)
     * @return
     * @throws SQLException 
     */
    public boolean clearDB() throws SQLException {
        this.connection.close();
        File dbFile = new File(this.dbName + ".db");
        dbFile.delete();
        return true;
    }
    
    //========================================================================== Print query
    public void printQuery() {
        //System.out.println("KYSELY: " + this.query);
    }
    
    public String getUserTableName() {
        return this.userFields.get(0);
    }
    
    public String getScoreTableName() {
        return this.scoreFields.get(0);
    }
}
