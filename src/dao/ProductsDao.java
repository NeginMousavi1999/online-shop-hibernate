package dao;

import model.products.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public abstract class ProductsDao extends BaseDao {
    public List<Product> readAll(String tableName) {
        Session session = sessionFactory.openSession();
        List<Product> result;
        Transaction transaction = session.beginTransaction();
        String hql = String.format("FROM %s", tableName);
        System.out.println(hql);
        Query<Product> query = session.createQuery(hql, Product.class);
        result = query.list();
        transaction.commit();
        session.close();
        return result;
    }

    public void increaseTheCountOfProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(product);
        transaction.commit();
        session.close();
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