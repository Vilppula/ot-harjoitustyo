package laatikkopeli.dao;

import laatikkopeli.domain.User;

public interface UserDao {
    
    User create(User user) throws Exception;
    
}
