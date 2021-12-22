package service;

import dao.CartDao;
import model.Cart;
import model.enums.CartStatus;
import model.person.User;
import model.products.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CartService {
    CartDao cartDao = new CartDao();
    ProductService productService = new ProductService();

    public CartService() throws SQLException, ClassNotFoundException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        return cartDao.findCountOfItemsByUserId(id);
    }

    public void addNewCart(Cart cart) {
//        cartDao.create(user, product, count);
        cartDao.create(cart);
//        reduceTheCountOfAvailableProduct(product, count);
    }

    public void reduceTheCountOfAvailableProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        productService.reduceTheCountOfProduct(product, count);
    }

    public void increaseTheCountOfAvailableProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        productService.increaseTheCountOfProduct(product, count);
    }

    public List<Cart> getNotCompletedCart(User user) throws SQLException {
        return cartDao.getCartsByStatus(user, CartStatus.NOT_COMPLETED);
    }

    public void removeCart(Cart cart) throws SQLException, ClassNotFoundException {
        cartDao.remove(cart);
        Product productInCart = cart.getProduct();
        System.out.println(productInCart.toString());
        Product product = productService.findProductById(productInCart.getTypeOfProducts().toString(),
                productInCart.getId());
        increaseTheCountOfAvailableProduct(product, productInCart.getCount());
    }

    public List<Cart> getCompletedCart(User user) throws SQLException {
        return cartDao.getCartsByStatus(user, CartStatus.COMPLETED);
    }

    public void confirmCarts(User user) throws SQLException {
        cartDao.updateStatus(user);
    }
}
