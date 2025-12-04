package docurepo.controller;

import docurepo.model.Admin;
import docurepo.model.Editor;
import docurepo.model.User;
import docurepo.model.Viewer;
import java.util.*;

public class UserController {
    public static UserController Instance;
    public static void Init() {
        Instance = new UserController();
        InitUsers();
    }

    private static ArrayList<User> users;
    private static void InitUsers() {
        users = new ArrayList<>();
        users.add(new Admin("admin", "admin"));
    }

    public void AddUser(String username, String password) {
        users.add(new User(username, password));
    }

    // public User authenticate(String username, String password) {
    //     for (User u : users) {
    //         if (u.GetUsername().equals(username) && u.GetPassword().equals(password)) {
    //             return u;
    //         }
    //     }
    //     return null;
    // }

    public boolean IsAdmin(User user) {
        return user instanceof Admin;
    }

    public boolean IsEditor(User user) {
        return user instanceof Editor;
    }

    public boolean IsViewer(User user) {
        return user instanceof Viewer;
    }

    public boolean IsUserValid(User user){
        for (User storedUser: users) {
            if (
                user.GetUsername().equals(storedUser.GetUsername()) &&
                user.GetPassword().equals(storedUser.GetPassword())
            )
                return true;
        }
        return false;
    }

    public boolean IsUserValid(String username, String password){
        return IsUserValid(new User(username, password));
    }
}