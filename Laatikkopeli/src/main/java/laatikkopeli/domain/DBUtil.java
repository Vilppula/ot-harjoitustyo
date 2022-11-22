package laatikkopeli.domain;

//Responsibility of the class: connection to database (sqlite)

public class DBUtil {

    public boolean connectDB() {       //Connect to DB
        return false;
    }
    
    public boolean addPlayer() {        //Add new player to database
        return false;
    }
    
    public void updateHighscore() {     //Method to update highscores
        
    }
    
    public boolean disconnectDB() {     //Disconnect
        return false;
    }
    
    public boolean initializeDB() {     //This method will remove existing DB and make new with required tables.
        return false;
    }
}
