package dao;

import model.products.Shoe;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class ShoeDao extends ProductsDao {
    public void create(Shoe shoe) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(shoe);
        transaction.commit();
        session.close();
    }

    public void delete(Shoe shoe) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(shoe);
        transaction.commit();
        session.close();
    }
}
