package model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.TypeOfProducts;
import model.enums.TypeOfShoe;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
@NoArgsConstructor
public class Shoe extends Product {
    private int sizeOfShoe;
    private String color;
    @Enumerated(value = EnumType.STRING)
    private TypeOfShoe typeOfShoe;

    public Shoe(int count, double cost, int sizeOfShoe, String color, TypeOfShoe typeOfShoe) {
        super(count, cost);
        this.sizeOfShoe = sizeOfShoe;
        this.color = color;
        this.typeOfShoe = typeOfShoe;
        typeOfProducts = TypeOfProducts.SHOES;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                super.toString() +
                ", sizeOfShoe=" + sizeOfShoe + ' ' +
                ", color=" + color + ' ' +
                ", typeOfShoe=" + typeOfShoe.toString().toLowerCase() + ' ' +
                '}';
    }
}
