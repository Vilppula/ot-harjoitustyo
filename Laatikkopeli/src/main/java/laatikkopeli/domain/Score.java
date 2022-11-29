package laatikkopeli.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Score extends DBobject{
    
    private final String username;
    private final String modeType;
    private final int levelID;
    private final Timestamp timestamp;
    private int points;

    public Score(String username, String modeType, int levelID, Timestamp timestamp, int points) {
        this.username = username;
        this.modeType = modeType;
        this.levelID = levelID;
        this.timestamp = timestamp;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public String getModeType() {
        return modeType;
    }

    public int getLevelID() {
        return levelID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getPoints() {
        return points;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.modeType, other.modeType)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Score{" + "username=" + username + ", modeType=" + modeType + ", timestamp=" + timestamp + '}';
    }
}
