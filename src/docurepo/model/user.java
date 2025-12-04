package docurepo.model;

public class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String GetUsername() { return username; }
    public String GetPassword() { return password; }
}
