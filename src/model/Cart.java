package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.CartStatus;
import model.person.User;
import model.products.Product;

import javax.persistence.*;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Enumerated(value = EnumType.STRING)
    private CartStatus cartStatus;

    public Cart(int id, Product product, CartStatus cartStatus) {
        this.id = id;
        this.product = product;
        this.cartStatus = cartStatus;
    }
}
