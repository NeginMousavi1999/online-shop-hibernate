package dao;

import model.products.ElectronicDevice;
import model.products.ReadableItem;
import model.products.Shoe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ReadableItemDao extends ProductsDao {
    public ReadableItemDao() throws ClassNotFoundException, SQLException {
    }

    public void create(ReadableItem readableItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(readableItem);
        transaction.commit();
        session.close();
    }

    public void delete(ReadableItem readableItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(readableItem);
        transaction.commit();
        session.close();
    }
}
