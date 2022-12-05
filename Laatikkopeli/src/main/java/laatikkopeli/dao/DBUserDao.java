package laatikkopeli.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.User;

public class DBUserDao implements UserDao {

    private final DBhandler dbhandler;
    private List<User> users;                               //Runtime users-list working as cache

    public DBUserDao(DBhandler dbhandler) {
        this.dbhandler = dbhandler;
        this.findAll();                                     
    }

    @Override
    public boolean saveUser(User user) {
        if (this.users.contains(user)) {
            return false;
        }
        boolean success = this.dbhandler.newRecord(user);   //Ask for insertion of new DBobject
        if (success) {                                      //If successful, add user to runtimelist
            this.users.add(user);       
        }                  
        return success;
    }

    @Override
    public User findUser(User user) {                                           //Checks if user is in cache
        System.out.println("Kysely: pelaaja " + user.getUsername() + ": " + this.users.contains(user));
        if (this.users.contains(user)) {
            return this.users.get(this.users.indexOf(user));
        }
        return null;
    }

    @Override
    public void findAll() {                                                     //Load users to cache
        ResultSet results = this.dbhandler.getAll("users");
        if (results == null) {
            System.out.println("TYHJÄÄ TÄYNNÄ");
        }
        List<User> userlist = new ArrayList<>();
        try {
            while (results.next()) {
                userlist.add(new User(results.getString(1), results.getString(2), results.getString(3)));
            }
            this.users = userlist;
        } catch (SQLException ex) {
            return;
        }
    }
}
