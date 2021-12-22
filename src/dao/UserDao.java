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

/*    public User findByUsername(String username) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserAndReturn(resultSet);
            }
        }
        return null;
    }*/

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

/*    public User createUserAndReturn(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                new Address(resultSet.getString(4)), UserRole.valueOf(resultSet.getString(5)));
    }*/

/*    public void create(User user) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`users` (`username`, `password`) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        }
    }*/

    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        session.saveOrUpdate(user.getAddress());
        transaction.commit();
        session.close();
    }


/*    public User findUser(String username, String password) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserAndReturn(resultSet); //reusing code :)
            }
        }
        return null;
    }*/

    /*    public List<User> readAll() throws SQLException {
            List<User> users = new ArrayList<>();
            if (connection != null) {
                String sql = "SELECT * FROM users;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    users.add(createUserAndReturn(resultSet));
                }
            }
            return users;

        }*/
    public List<User> readAll() {
        Session session = sessionFactory.openSession();
        List<User> result;
        session.beginTransaction();
        String hql = "FROM User";
        System.out.println(hql);
        Query<User> query = session.createQuery(hql);
        result = query.list();
        return result;
    }
}
