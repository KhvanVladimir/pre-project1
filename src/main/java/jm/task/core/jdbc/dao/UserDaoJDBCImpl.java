package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "users", new String[]{"TABLE"});
            if (!resultSet.next()) {
                connection.createStatement().execute("CREATE TABLE Users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (`id`))");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при попытке создания таблицы");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().execute("drop table if exists users");
        } catch (SQLException e) {
            System.out.println("Ошибка при попытке удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String query = String.format("INSERT INTO users values(null, '%s', '%s', '%d')", name, lastName, age);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.out.println("Ошибка при попытке сохранить пользователя");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            String query = String.format("DELETE FROM users WHERE id = %x", id);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.out.println("Ошибка при попытке удалить пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> l = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            ResultSet result = connection.createStatement().executeQuery("select * from users");
            while (result.next()) {
                User u = new User();
                u.setId(result.getLong("id"));
                u.setName(result.getString("name"));
                u.setLastName(result.getString("lastName"));
                u.setAge(result.getByte("age"));
                l.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Проблема ри попытке получить всех пользователей");
        }
        return l;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Ошибка при попытке очистить таблицу пользователей");
        }
    }
}
