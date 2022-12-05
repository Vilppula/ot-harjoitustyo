package laatikkopeli.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    
    User user;
    
    @Before
    public void setUp() {
        this.user = new User("name", "pass", "path");
    }
    
    @Test
    public void createdUserHasCorrectFields() {
        assertEquals("name", this.user.getUsername());
        assertEquals("pass", this.user.getPassword());
        assertEquals("path", this.user.getAvatarURL());
    }
    
    @Test
    public void avatarPathCanBeChanged() {
        this.user.setAvatarURL("newpath");
        assertEquals("newpath", this.user.getAvatarURL());
    }
    
    @Test
    public void comparingUserToNullReturnsFalse() {
        assertEquals(false, this.user.equals(null));
    }
    
    @Test
    public void comparingUserToItselfReturnsTrue() {
        assertEquals(true, this.user.equals(this.user));
    }
    
    @Test
    public void comparingUserToWrongClassReturnsFalse() {
        assertEquals(false, this.user.equals(new String("")));
    }
    
    @Test
    public void comparingUserToOtherWithSameNameReturnsTrue() {
        assertEquals(true, this.user.equals(new User("name", "piss", "bath")));
    }
}
