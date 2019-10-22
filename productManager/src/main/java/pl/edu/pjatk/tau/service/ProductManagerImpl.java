package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerImpl implements ProductManager {

    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) throws Exception {
        if (getProductById(product.getId()) != null) {
            throw new Exception();
        }
        products.add(product);
    }

    @Override
    public void updateProduct(Product productChanged) throws NullPointerException {
        Product product = getProductById(productChanged.getId());
        if (product != null){
            product.setName(productChanged.getName());
            product.setDescription(productChanged.getDescription());
        } else {
            throw new NullPointerException(); }
    }

    @Override
    public void deleteProduct(int id) {
        Product product = getProductById(id);
        if (product != null) {
            products.remove(product);
        }
    }
}
