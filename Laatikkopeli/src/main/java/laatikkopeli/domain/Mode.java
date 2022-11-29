package laatikkopeli.domain;

import laatikkopeli.domain.User;

public class Mode {

    private String modeType;
    private User user1;
    private User user2;

    public Mode(String modeType, User user1) {
        this.modeType = modeType;
        this.user1 = user1;
    }
    
    public Mode(String modeType, User user1, User user2) {
        this.modeType = modeType;
        this.user1 = user1;
        this.user2 = user2;
    }
    
}
