package laatikkopeli.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.Score;

/**
 * Data access for Score-type objects
 * @author lasse
 */
public class DBScoreDao implements ScoreDao {

    private DBhandler handler;
    private List<Score> scores;
    private Map<Integer, List<Score>> areaScores;                               //Here are scores mapped by areaname
    private Map<String, List<Score>> userScores;                                //Here are scores mapped by username
    private Comparator<Score> scoreComparator = Comparator
        .comparing(Score::getAreaId)                                            //Use this comparator for cache initialization
        .thenComparing(Score::getPoints);
    private String scoreTable;
    
    public DBScoreDao(DBhandler handler) {
        this.handler = handler;
        this.scoreTable = handler.getScoreTableName();
        this.areaScores = new HashMap<>();
        this.userScores = new HashMap<>();
    }
    
    /**
     * Method returns all scores by User
     * @param user
     * @return 
     */
    @Override
    public List<Score> findByUser(String username) {
        if (this.userScores.get(username) == null) {
            this.userScores.put(username, castToScore(handler.getScores(username)));
        }
        return this.userScores.get(username);
    }

    /**
     * Method returns all scores by GameLayout
     * @param gameLayout
     * @return 
     */
    @Override
    public List<Score> findByGameArea(int areaId) {
        if (this.areaScores.get(areaId) == null) {
            this.areaScores.put(areaId, castToScore(handler.getScores(areaId)));
        }
        return this.areaScores.get(areaId);
    }

    /**
     * Method to save new highscore to DB and cache
     * @param score
     * @return 
     */
    @Override
    public boolean addScore(Score score) {                                      //Top 10 check is done before this method is being called
        List<Score> newAreaTopScores = findByGameArea(score.getAreaId());
        List<Score> newUserTopScores = findByUser(score.getUsername());
        //Reject duplicates
        if (newAreaTopScores.contains(score)){
            return false;
        }    
        //Check if score is worth top 10
        newAreaTopScores.add(score);                                            //Add new score
        newUserTopScores.add(score);                                            //Add score to user
        if (newAreaTopScores.size() <= 10) {                                    //Check whether there are top 10 scores yet
            if (handler.newOrUpdate(score, true)) {
                return true;
            }    
        }
        newAreaTopScores = streamTopTen(newAreaTopScores);                      //Sort
        if (newAreaTopScores.contains(score)) {                                 
            
            handler.newOrUpdate(score, true);
        }
        this.userScores.put(score.getUsername(), newUserTopScores);             //Add to map
        this.areaScores.put(score.getAreaId(), newAreaTopScores);               //Add to map
        return true;
    }
    
    /**
     * Stream List<Score> sorted and limited to 10
     * @param list
     * @return 
     */
    private List<Score> streamTopTen(List<Score> list) {
        if (list == null) return new ArrayList<>();
        return list.stream()                                                    //Sort list by points, get top 10
                .sorted((s1, s2)->s1.getPoints().compareTo(s2.getPoints()))
                .limit(10)
                .collect(Collectors.toList());
    }
    
    /**
     * Helper method to cast List<DBobject> to List<Score>
     * @param list
     * @return 
     */
    private List<Score> castToScore(List<DBobject> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(Score.class::cast)
                .collect(Collectors.toList());
    }
}
