package dao;

import model.enums.BrandOfDevice;
import model.enums.TypeOfReadableItem;
import model.enums.TypeOfShoe;
import model.person.User;
import model.products.ElectronicDevice;
import model.products.Product;
import model.products.ReadableItem;
import model.products.Shoe;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public abstract class ProductsDao extends BaseDao {
    public ProductsDao() throws ClassNotFoundException, SQLException {
    }

    public List<Product> readAll(String tableName) {
        Session session = sessionFactory.openSession();
        List<Product> result;
        session.beginTransaction();
        String hql = String.format("FROM %s", tableName);
        System.out.println(hql);
        Query<Product> query = session.createQuery(hql, Product.class);
        result = query.list();
        return result;
    }

    public void reduceTheCountOfProduct(Product product, int countToReduce) throws SQLException {
        if (connection != null) {
            String sql = String.format("UPDATE %s SET count = count - %o WHERE id = ?;", product.getTypeOfProducts().toString().toLowerCase(),
                    countToReduce);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
    }

    public void increaseTheCountOfProduct(Product product, int countToIncrease) throws SQLException {
        if (connection != null) {
            String sql = String.format("UPDATE %s SET count = count + %o WHERE id = ?;", product.getTypeOfProducts().toString().toLowerCase(),
                    countToIncrease);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
    }

    public Product findById(String tableName, int id) {
        Session session = sessionFactory.openSession();
        List<Product> result;
        session.beginTransaction();
        String hql = String.format("from %s p where p.id=:id", tableName);
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("id", id);
        result = query.list();
        assert result != null;
        try {
            return result.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}