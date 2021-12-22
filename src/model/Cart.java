package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.CartStatus;
import model.person.User;
import model.products.Product;

import javax.persistence.*;

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
    @OneToOne
    private Product product;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private CartStatus cartStatus;
    private int count;
}
