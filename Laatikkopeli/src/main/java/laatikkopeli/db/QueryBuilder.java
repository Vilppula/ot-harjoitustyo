package laatikkopeli.db;

import java.util.List;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;

/**
 * Helper class to build Strings used in JDBC
 * @author lasse
 */
public class QueryBuilder {
    
    private String query;
    private final String userTable;
    private final String scoreTable;

    public QueryBuilder(String userTable, String scoreTable) {
        this.userTable = "users";
        this.scoreTable = "scores";
    }
    
    //========================================================================== INSERTS
    /**
     * Build query to insert User-type object
     * @param user
     * @return 
     */
    public String newInsertQuery(User user) {
        this.query = "INSERT INTO " + this.userTable + " (username, password, avURL)\n"
            + "    VALUES ('"
            + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getAvatarURL() + "')";
        return this.query;
    }
    /**
     * Build query to insert Score-type object
     * @param score
     * @return 
     */
    public String newInsertQuery(Score score) {
        this.query = "INSERT INTO " + this.scoreTable + " (username, modeType, levelID, datetime, points)\n"
            + "    VALUES ('"
            + score.getUsername() + "', '" + score.getModeType() + "', "
            + score.getAreaId() + ", '" + score.getDatetime() + "', " + score.getPoints() + ")";
        return this.query;
    }
    //========================================================================== SELECTS
    /**
     * Build select query for all User-type objects
     * @return 
     */
    public String newSelectUserQuery() {                        //Get all users
        this.query = "SELECT * FROM " + this.userTable;
        return this.query;
    }
    /**
     * Build select query for User-type object by username
     * @param username
     * @return 
     */
    public String newSelectUserQuery(String username) {         //Get one user
        this.query = "SELECT * FROM " + this.userTable + " WHERE username='" + username + "'";
        return this.query;
    }
    
     /**
     * Build select query for all Score-type objects
     * @return 
     */
    public String newSelectScoreQuery() {                       //Get all scores
        this.query = "SELECT * FROM " + this.scoreTable;
        return this.query;
    }
    
    /**
     * Build select query for Score-type object related to User
     * @param username
     * @return 
     */
    public String newSelectScoreQuery(String username) {        //Get scores by username
        this.query = "SELECT * FROM " + this.scoreTable + " WHERE username=" + username;
        return this.query;
    }
    
    /**
     * Build select query for Score-type object related to game areaa
     * @param username
     * @return 
     */
    public String newSelectScoreQuery(int levelID) {            //Get scores by level id
        this.query = "SELECT * FROM " + this.scoreTable + " WHERE levelID=" + levelID;
        return this.query;
    }
    //========================================================================== UPDATES
    /**
     * Create update query to replace lowest top 10 score with new one
     * @param score
     * @return 
     */
    public String newUpdateScoresQuery(Score score) {
        this.query = "UPDATE " + this.scoreTable + "\n"
                + "SET username = '" + score.getUsername()
                + "', modetype = '" + score.getModeType()
                + "', datetime = " + score.getDatetime()
                + ", points = " + score.getPoints()
                + "\nWHERE rowid = (SELECT rowid FROM " + this.scoreTable 
                + " WHERE levelID = " + score.getAreaId() + " AND "
                + " points < " + score.getPoints()
                + " ORDER BY points ASC LIMIT 1)";
        return this.query;
    }
    //========================================================================== TABLE CREATION (from list of fields
    /**
     * Init method to create required SQL tables
     * @param tableFields
     * @return 
     */
    public String newCreateTableQuery(List<String> tableFields) {
        this.query = "CREATE TABLE IF NOT EXISTS " + tableFields.get(0) + " (\n";
        for (int i = 1; i < tableFields.size(); i += 2) {
            this.query += "    " + tableFields.get(i) + " " + tableFields.get(i + 1) + ",\n";
        }
        this.query = this.query.substring(0, this.query.length() - 2) + "\n)";
        return this.query;
    }
}
