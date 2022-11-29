package laatikkopeli.dao;

import laatikkopeli.domain.User;

public interface UserDao {
    
    boolean saveUser(User user) throws Exception;
    
    User findUser(User user);
    
    void findAll();
}
