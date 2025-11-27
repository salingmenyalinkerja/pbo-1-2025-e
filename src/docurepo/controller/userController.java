package docurepo.controller;

import docurepo.model.User;
import java.util.*;

public class UserController {
    private List<User> users = new ArrayList<>();

    public void addUser(String username, String password, String role) {
        users.add(new User(username, password, role));
    }

    public User authenticate(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean isAdmin(User user) {
        return "Admin".equals(user.getRole());
    }

    public boolean isEditor(User user) {
        return "Editor".equals(user.getRole());
    }
}