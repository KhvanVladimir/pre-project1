package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao a = new UserDaoJDBCImpl();

    public void createUsersTable() {
        a.createUsersTable();
    }

    public void dropUsersTable() {
        a.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        a.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        a.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return a.getAllUsers();
    }

    public void cleanUsersTable() {
        a.cleanUsersTable();
    }
}
