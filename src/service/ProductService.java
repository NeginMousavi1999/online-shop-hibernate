package service;

import dao.ElectronicDeviceDao;
import dao.ProductsDao;
import dao.ReadableItemDao;
import dao.ShoeDao;
import model.products.ElectronicDevice;
import model.products.Product;
import model.products.ReadableItem;
import model.products.Shoe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class ProductService {
    ReadableItemDao readableItemDao = new ReadableItemDao();
    ShoeDao shoeDao = new ShoeDao();
    ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();

    public List<Product> returnAllProducts() {
        List<Product> products = new ArrayList<>();
        products.addAll(electronicDeviceDao.readAll("ElectronicDevice"));
        products.addAll(shoeDao.readAll("Shoe"));
        products.addAll(readableItemDao.readAll("ReadableItem"));
        return products;
    }

    public void increaseTheCountOfProduct(Product product, int count) {
        int newCount = product.getCount() + count;
        product.setCount(newCount);
        returnProductsDao().increaseTheCountOfProduct(product);
    }

    public Product findProductById(String tableName, int id) {
        return returnProductsDao().findById(tableName, id);
    }

    public ProductsDao returnProductsDao() {
        return new ProductsDao() {
        };
    }

    public void addNewElectronicProduct(ElectronicDevice electronicDevice) {
        electronicDeviceDao.create(electronicDevice);
    }

    public void addNewReadableProduct(ReadableItem readableItem) {
        readableItemDao.create(readableItem);
    }

    public void addNewShoeProduct(Shoe shoe) {
        shoeDao.create(shoe);
    }

    public void removeElectronicDevice(ElectronicDevice electronicDevice) {
        electronicDeviceDao.delete(electronicDevice);
    }

    public void removeReadableItem(ReadableItem readableItem) {
        readableItemDao.delete(readableItem);
    }

    public void removeShoe(Shoe shoe) {
        shoeDao.delete(shoe);
    }
}
