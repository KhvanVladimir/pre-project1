package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (SessionFactory connection = Util.getSessionFactory()) {
            Session session = connection.openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Problems create");
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory connection = Util.getSessionFactory()) {
            Session session = connection.openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception a) {
            System.out.println("problems");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory connection = Util.getSessionFactory()) {
            Session s = connection.openSession();
            s.beginTransaction();
            User u = new User(name, lastName, age);
            s.save(u);
            s.getTransaction().commit();
            s.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory connection = Util.getSessionFactory()) {
            Session s = connection.openSession();
            s.createQuery("from User where id = :id").setParameter("id", id);
            s.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory connection = Util.getSessionFactory()) {
           return connection.openSession().createQuery("from User").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory connection = Util.getSessionFactory()) {
            Session session = connection.openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE from Users";

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception a) {
            System.out.println("problems");
        }
    }
}
