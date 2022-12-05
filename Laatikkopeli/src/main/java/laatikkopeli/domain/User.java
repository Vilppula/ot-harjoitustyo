
package laatikkopeli.domain;

import java.util.Objects;

//This class is describing user-object.
public class User extends DBobject{
    
    private String username;
    private String password;
    private String avatarURL;
    
    public User(String username, String password, String avatarURL){
        this.password = password;
        this.username = username;
        this.avatarURL = avatarURL;
    }


    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
}
