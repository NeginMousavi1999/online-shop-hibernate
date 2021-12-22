package dao;

import model.products.ReadableItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class ReadableItemDao extends ProductsDao {
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
