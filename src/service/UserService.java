package service;

import dao.UserDao;
import model.enums.UserRole;
import model.person.Address;
import model.person.User;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class UserService {
    UserDao userDao = new UserDao();
    CartService cartService = new CartService();

    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void addNewUser(User user) {
        userDao.create(user);
    }

    public int findCountOfItemsInUserCart(User user) {
        return cartService.findCountOfItemsByUserId(user.getId());
    }

    public CartService accessToCartService() {
        return cartService;
    }

    public List<User> returnAllUsers() {
        return userDao.readAll();
    }

    public void initAdmin() {
        User admin = userDao.getAdmin();
        if (admin == null)
            createAdmin();
    }

    private void createAdmin() {
        Address address = new Address();
        address.setPostalCode("123456789");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setUserRole(UserRole.ADMIN);
        user.setAddress(address);
        address.setUser(user);
        userDao.create(user);
    }
}
