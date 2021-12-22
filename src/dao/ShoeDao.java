package dao;

import model.person.User;
import model.products.ElectronicDevice;
import model.products.Shoe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ShoeDao extends ProductsDao {
    public ShoeDao() throws ClassNotFoundException, SQLException {
    }

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
