package model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.TypeOfProducts;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    protected int count;
    protected double cost;
    @Enumerated(value = EnumType.STRING)
    protected TypeOfProducts typeOfProducts;

    public Product(int count, double cost) {
        this.count = count;
        this.cost = cost;
    }

    public Product(int id, int count, double cost) {
        this(count, cost);
        this.id = id;
    }

    public Product(int id, int count, double cost, TypeOfProducts typeOfProducts) {
        this(id, count, cost);
        this.typeOfProducts = typeOfProducts;
    }

    @Override
    public String toString() {
        return "id=" + id + ' ' +
                ", count=" + count + ' ' +
                ", cost=" + cost + ' ' +
                ", typeOfProducts=" + typeOfProducts.toString().toLowerCase() + ' ';
    }

    public void buy(int count) {
        this.count -= count;
    }
}
