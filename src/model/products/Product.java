package model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.TypeOfProducts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Negin Mousavi
 */
@Data
public class Product {
    private int id;
    protected int count;
    protected double cost;
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
}
