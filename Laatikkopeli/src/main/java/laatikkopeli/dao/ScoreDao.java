package laatikkopeli.dao;

import java.util.List;
import laatikkopeli.domain.GameLayout;
import laatikkopeli.domain.Score;
import laatikkopeli.domain.User;

/**
 * Score data access interface
 * @author lasse
 */
public interface ScoreDao {
    
   
    public boolean addScore(Score score);
    
    public List<Score> findByUser(String username);
    
    public List<Score> findByGameArea(int areaId);
    
}
