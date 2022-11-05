package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {;
        Util.action("create");
    }

    public void dropUsersTable() {
        Util.action("drop");
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        Util.removeUser(id);
    }

    public List<User> getAllUsers() {
        return Util.getUsers();
    }

    public void cleanUsersTable() {
        Util.cleanTable();
    }
}
