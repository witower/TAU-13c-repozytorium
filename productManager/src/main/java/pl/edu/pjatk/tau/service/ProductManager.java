package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Product;

import java.util.List;

public interface ProductManager {

    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(Product product) throws Exception;
    void updateProduct(Product product) throws NullPointerException;
    void deleteProduct(int id);

}
