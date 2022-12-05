package laatikkopeli.domain;

import java.util.Objects;

public class Score extends DBobject {
    
    private final String username;
    private final String modeType;
    private final int levelID;
    private final String datetime;
    private int points;

    public Score(String username, String modeType, int levelID, String datetime, int points) {
        this.username = username;
        this.modeType = modeType;
        this.levelID = levelID;
        this.datetime = datetime;
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

    public String getDatetime() {
        return datetime;
    }

    public int getPoints() {
        return points;
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
        if (!Objects.equals(this.datetime, other.datetime)) {
            return false;
        }
        return true;
    }
}
