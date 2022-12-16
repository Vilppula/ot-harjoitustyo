package laatikkopeli.db;

import java.util.List;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;

public class QueryBuilder {
    
    private String query;
    private final String userTable;
    private final String scoreTable;

    public QueryBuilder(String userTable, String scoreTable) {
        this.userTable = "users";
        this.scoreTable = "scores";
    }
    
    //========================================================================== INSERTS
    public String newInsertQuery(User user) {
        this.query = "INSERT INTO " + this.userTable + " (username, password, avURL)\n"
            + "    VALUES ('"
            + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getAvatarURL() + "')";
        return this.query;
    }
    public String newInsertQuery(Score score) {
        this.query = "INSERT INTO " + this.scoreTable + " (username, modeType, levelID, datetime, points)\n"
            + "    VALUES ('"
            + score.getUsername() + "', '" + score.getModeType() + "', "
            + score.getAreaId() + ", '" + score.getDatetime() + "', " + score.getPoints() + ")";
        return this.query;
    }
    //========================================================================== SELECTS
    public String newSelectUserQuery() {                        //Get all users
        this.query = "SELECT * FROM " + this.userTable;
        return this.query;
    }
    public String newSelectUserQuery(String username) {         //Get one user
        this.query = "SELECT * FROM " + this.userTable + " WHERE username='" + username + "'";
        return this.query;
    }
    
    public String newSelectScoreQuery() {                       //Get all scores
        this.query = "SELECT * FROM " + this.scoreTable;
        return this.query;
    }
    public String newSelectScoreQuery(String username) {        //Get scores by username
        this.query = "SELECT * FROM " + this.scoreTable + "WHERE username=" + username;
        return this.query;
    }
    public String newSelectScoreQuery(int levelID) {            //Get scores by level id
        this.query = "SELECT * FROM " + this.scoreTable + " WHERE levelID=" + levelID;
        return this.query;
    }
    //========================================================================== TABLE CREATION (from list of fields
    public String newCreateTableQuery(List<String> tableFields) {
        this.query = "CREATE TABLE IF NOT EXISTS " + tableFields.get(0) + " (\n";
        for (int i = 1; i < tableFields.size(); i += 2) {
            this.query += "    " + tableFields.get(i) + " " + tableFields.get(i + 1) + ",\n";
        }
        this.query = this.query.substring(0, this.query.length() - 2) + "\n)";
        return this.query;
    }
}
