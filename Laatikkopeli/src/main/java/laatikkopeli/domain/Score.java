package laatikkopeli.domain;

import java.util.Objects;

public class Score extends DBobject {
    
    private final String username;
    private final String modeType;
    private final Integer areaId;
    private final String datetime;
    private Integer points;

    public Score(String username, String modeType, int areaId, String datetime, int points) {
        this.username = username;
        this.modeType = modeType;
        this.areaId = areaId;
        this.datetime = datetime;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public String getModeType() {
        return modeType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public String getDatetime() {
        return datetime;
    }

    public Integer getPoints() {
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
        if (Objects.equals(this.username, other.username)
                && this.areaId == other.areaId
                && Objects.equals(this.datetime, other.datetime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Score{" + "username=" + username + ", modeType=" + modeType + ", areaId=" + areaId + ", datetime=" + datetime + ", points=" + points + '}';
    }
    
}
