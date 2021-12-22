package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public abstract class BaseDao {
    protected Connection connection;
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public BaseDao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "123456");
    }
}