package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        String name;

        UserDao user = new UserDaoJDBCImpl();

        user.createUsersTable();

        while (i != 3) {
            name = String.format("Alex%d", i);
            user.saveUser(name, "A", (byte) i);
            System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
            i++;
        }

        List<User> l = user.getAllUsers();
        for (User u : l) {
            System.out.println(u.toString());
        }

        user.cleanUsersTable();

        user.dropUsersTable();

    }
}
