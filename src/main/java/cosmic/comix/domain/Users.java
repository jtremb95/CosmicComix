package cosmic.comix.domain;

/**
 * Created by NSchneier on 6/3/2015.
 */
public class Users extends AbstractDomain {
    private String username;
    private String password;

    public Users() {
        this.username = "";
        this.password = "";
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
