package dao;

import model.Cart;
import model.enums.CartStatus;
import model.person.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CartDao extends BaseDao {

    public int findCountOfItemsByUserId(int id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Cart.class, "c");
        SimpleExpression userIdCond = Restrictions.eq("c.user.id", id);
        criteria.add(userIdCond);
        criteria.setProjection(Projections.rowCount());
        return (Integer) criteria.uniqueResult();
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

    public List<Cart> getCartsByStatus(User user, CartStatus cartStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Cart c where c.user.id=:userId and c.cartStatus=:cartStatus";
        Query<Cart> query = session.createQuery(hql, Cart.class);
        query.setParameter("userId", user.getId());
        query.setParameter("cartStatus", cartStatus);
        List<Cart> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void remove(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cart);
        transaction.commit();
        session.close();
    }

    public void updateStatus(List<Cart> carts) {
        for (Cart cart : carts) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(cart);
            transaction.commit();
            session.close();
        }
    }
}
