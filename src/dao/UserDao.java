package dao;

import model.person.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class UserDao extends BaseDao {
    public UserDao() throws ClassNotFoundException, SQLException {
    }

    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        List<User> result;
        session.beginTransaction();
        String hql = "from User user where user.username=:username";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        result = query.list();
        assert result != null;
        try {
            return result.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        session.saveOrUpdate(user.getAddress());
        transaction.commit();
        session.close();
    }

    public List<User> readAll() {
        Session session = sessionFactory.openSession();
        List<User> result;
        session.beginTransaction();
        String hql = "FROM User";
        System.out.println(hql);
        Query<User> query = session.createQuery(hql, User.class);
        result = query.list();
        return result;
    }
}
