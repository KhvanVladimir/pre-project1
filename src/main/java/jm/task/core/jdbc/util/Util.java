package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/test1?useSSL=false";
    private static final String PASSWORD = "admin";
    private static final String LOGIN = "admin";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static Connection connection;
    private static SessionFactory sessionFactory;
    private Util() {

    }
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Не удалось получить подключение к БД");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration c = new Configuration();
            Properties setting = new Properties();
            setting.put(Environment.URL, URL);
            setting.put(Environment.USER, LOGIN);
            setting.put(Environment.PASS, PASSWORD);
            setting.put(Environment.DRIVER, DRIVER);
            setting.put(Environment.DIALECT, DIALECT);
            c.setProperties(setting);
            c.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(c.getProperties()).build();

           sessionFactory = c.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            System.out.println("Не удалось получить подключение к БД через фабрику");
        }
        return sessionFactory;
    }


}
