package laatikkopeli.dao;

import java.util.List;
import java.util.stream.Collectors;
import laatikkopeli.db.DBhandler;
import laatikkopeli.domain.DBobject;
import laatikkopeli.domain.User;

public class DBUserDao implements UserDao {

    private final DBhandler handler;
    private String userTable;
    private List<User> users;                                                   //Runtime users-list working as cache

    
    public DBUserDao(DBhandler handler) {
        this.handler = handler;
        this.userTable = handler.getUserTableName();
        findAll();                                                              
    }

    @Override
    public boolean saveUser(User user) {
        if (this.users.contains(user)) {
            return false;
        }
        boolean success = this.handler.newRecord(user);                         //Ask for insertion of new DBobject
        if (success) {                                                          //If successful, add user to runtimelist
            this.users.add(user);       
        }                  
        return success;
    }

    @Override
    public User findUser(User user) {                                           //Checks if user is in cache
        if (this.users.contains(user)) {
            return this.users.get(this.users.indexOf(user));
        }
        User dbUser = (User) handler.getRecord(user.getUsername());
        if (dbUser != null) {
            this.users.add(dbUser);
        }
        return dbUser;
    }

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
