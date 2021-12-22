package dao;

import model.products.ElectronicDevice;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ElectronicDeviceDao extends ProductsDao {
    public ElectronicDeviceDao() throws ClassNotFoundException, SQLException {
    }

    public void create(ElectronicDevice electronicDevice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(electronicDevice);
        transaction.commit();
        session.close();
    }

    public void delete(ElectronicDevice electronicDevice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(electronicDevice);
        transaction.commit();
        session.close();
    }
}
