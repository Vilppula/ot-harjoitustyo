package laatikkopeli.dao;

import java.util.List;
import laatikkopeli.domain.User;

public interface UserDao {
    
    boolean saveUser(User user) throws Exception;
    
    User findUser(User user);
    
    List<User> findAll();
}
