package model.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.ProductSold;
import model.enums.UserRole;

import javax.persistence.*;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Transient
    private List<ProductSold> productSold;
    @OneToOne
    private Address address;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;


    public User(String username, String password, Address address, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.userRole = userRole;
    }

    public User(int id, String username, String password, Address address, UserRole userRole) {
        this(username, password, address, userRole);
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", productSold=" + productSold +
                ", userRole=" + userRole +
                '}';
    }
}