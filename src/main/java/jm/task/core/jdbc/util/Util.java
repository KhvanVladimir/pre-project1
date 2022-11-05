package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    private static String URL = "jdbc:mysql://localhost:3306/test1";
    private static String PASSWORD = "admin";
    private static String LOGIN = "root";
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Не удалось получить подключение к БД");
        }
    }
    public static void action(String action) {
        switch (action) {
            case "create": createTable();
            break;
            case "drop": dropTable();
                break;
            case "get": getUsers();
                break;
            case "clean": cleanTable();
                break;
        }
    }

    public static void createTable() {
        try {
            connection.createStatement().execute("CREATE TABLE Users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (`id`))");
        } catch (SQLException e) {
            System.out.println("Ошибка create");
        }
    }
    public static void dropTable() {
        try {
            connection.createStatement().execute("drop table if exists users");
        } catch (SQLException e) {
            System.out.println("Ошибка drop");
        }
    }
    public static void saveUser(String name, String lastName, byte age) {
        try {
            String query = String.format("INSERT INTO users values(null, '%s', '%s', '%d')", name, lastName, age);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.out.println("Ошибка save");
        }
    }
    public static void removeUser(long id) {
        try {
            String query = String.format("DELETE FROM users WHERE id = %x", id);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            System.out.println("Ошибка remove");
        }
    }
    public static List<User> getUsers() {
        List<User> l = new ArrayList<>();
        try {
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
            System.err.println("Проблема в get");
        }
        return l;
    }
    public static void cleanTable() {
        try {
            connection.createStatement().execute("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Ошибка remove");
        }
    }

}
