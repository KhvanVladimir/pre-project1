package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/test1";
    private static final String PASSWORD = "admin";
    private static final String LOGIN = "root";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Не удалось получить подключение к БД");
        }
    }

    private Util() {}

    public static Connection getConnection() {
        return connection;
    }

}
