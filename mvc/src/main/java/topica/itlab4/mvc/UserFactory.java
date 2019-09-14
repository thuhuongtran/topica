package topica.itlab4.mvc;

import topica.itlab4.mvc.entity.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserFactory {
    private Map<Integer, User> users;
    private static int ID = 0;

    public UserFactory() {
        users = new HashMap<>();
    }

    public void createUser(String name, String birthYear, String gender, String enterDate) {
        users.put(++ID, new User(ID, name, birthYear, gender, enterDate));
    }

    public void editUser(int id, String name, String birthYear, String gender, String enterDate) {
        users.replace(id, new User((ID), name, birthYear, gender, enterDate));
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    public List<User> searchByName(String text) {
        List<User> us = new ArrayList<>();
        for (User u : users.values()) {
            if (u.getName().toLowerCase().contains(text.toLowerCase())) {
                us.add(u);
            }
        }
        return us;
    }

    public List<User> getUsers() {
        List<User> us = new ArrayList<>(users.values());
        Collections.reverse(us);
        return us;
    }
}
