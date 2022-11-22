package laatikkopeli.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    
    User user;
    
    @Before
    public void setUp() {
        this.user = new User("RaunchyMOFO", "12345");
    }
    
    @Test
    public void createdUserHasCorrectName() {
        assertEquals("RaunchyMOFO", this.user.getUsername());
    }

    
}
