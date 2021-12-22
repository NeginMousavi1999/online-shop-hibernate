package dao;

import model.Cart;
import model.enums.CartStatus;
import model.enums.TypeOfProducts;
import model.person.User;
import model.products.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CartDao extends BaseDao {
    public CartDao() throws ClassNotFoundException, SQLException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        int count = 0;
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carts where user_id_fk=? AND status=\"NOT_COMPLETED\";");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                count++;
        }
        return count;
    }

    public void create(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cart);
        session.merge(cart.getProduct());
        session.merge(cart.getUser());
        transaction.commit();
        session.close();
    }

    public Cart createCartAndReturn(ResultSet resultSet, CartStatus cartStatus) throws SQLException {
//        List<Product> products = new ArrayList<>();
        Product product = new Product(resultSet.getInt(5), resultSet.getInt(3), resultSet.getDouble(4),
                TypeOfProducts.valueOf(resultSet.getString(2)));
//        products.add(e);
        return new Cart(resultSet.getInt(1), product, cartStatus);
    }

    public List<Cart> getCartsByStatus(User user, CartStatus cartStatus) throws SQLException {
        List<Cart> soldList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT id, product_type, count, cost, product_id_fk FROM carts WHERE user_id_fk=? AND status=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, cartStatus.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                soldList.add(createCartAndReturn(resultSet, cartStatus));
        }
        return soldList;
    }

    public void remove(Cart cart) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cart);
        transaction.commit();
        session.close();
    }

    public void updateStatus(User user) throws SQLException {
        if (connection != null) {
            String sql = "UPDATE carts SET status=\"COMPLETED\" WHERE user_id_fk=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        }
    }
}
