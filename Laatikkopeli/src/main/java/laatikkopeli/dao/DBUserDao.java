package laatikkopeli.dao;

import java.util.List;
import java.util.stream.Collectors;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.User;

/**
 * Data access for User-type objects
 * @author lasse
 */
public class DBUserDao implements UserDao {

    private final DBhandler handler;
    private String userTable;
    private List<User> users;                                                   //Runtime users-list working as cache

    
    public DBUserDao(DBhandler handler) {
        this.handler = handler;
        this.userTable = handler.getUserTableName();
        findAll();                                                              
    }

    /**
     * Save new user to database/ cahce
     * @param user
     * @return 
     */
    @Override
    public boolean saveUser(User user) {
        if (this.users.contains(user)) {
            return false;
        }
        boolean success = this.handler.newOrUpdate(user, true);                 //Ask for insertion of new DBobject
        if (success) {                                                          //If successful, add user to runtimelist
            this.users.add(user);       
        }                  
        return success;
    }

    /**
     * Find single user from database/ cache
     * @param user
     * @return 
     */
    @Override
    public User findUser(User user) {                                           //Checks if user is in cache
        if (this.users.contains(user)) {
            return this.users.get(this.users.indexOf(user));
        }
        return null;
    }

    /**
     * Find all User-type records from DB and return them as List<User>
     * @return 
     */
    @Override
    public List<User> findAll() {                                               //Load users to cache and return list
        if (this.users == null) {
            this.users = castToUser(this.handler.getAll(userTable));
        }
        return this.users;
    }
    
    /**
     * Helper method to cast List<DBobject> to List<User>
     * @param list
     * @return 
     */
    private List<User> castToUser(List<DBobject> list) {                      
        return list.stream().map(User.class::cast)
                .collect(Collectors.toList());
    }
}
