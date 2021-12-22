package model.products;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.BrandOfDevice;
import model.enums.TypeOfProducts;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Negin Mousavi
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "electronic_devices")
public class ElectronicDevice extends Product {
    @Enumerated(value = EnumType.STRING)
    private BrandOfDevice brandOfDevice;

    public ElectronicDevice(int count, double cost, BrandOfDevice brandOfDevice) {
        super(count, cost);
        this.brandOfDevice = brandOfDevice;
        typeOfProducts = TypeOfProducts.ELECTRONIC_DEVICES;
    }

    public ElectronicDevice(int id, int count, double cost, BrandOfDevice brandOfDevice) {
        super(id, count, cost);
        this.brandOfDevice = brandOfDevice;
        typeOfProducts = TypeOfProducts.ELECTRONIC_DEVICES;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                super.toString() +
                ", BrandOfDevice=" + brandOfDevice.toString().toLowerCase() +
                '}';
    }
}
