package pl.edu.pjatk.tau.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.edu.pjatk.tau.domain.Product;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ProductManagerImplTest {

    private ProductManagerImpl productManager;
    private Product testProduct;
    private static final int TEST_ID = 7;

    @Before
    public void prepareCommonTestData() throws Exception{
        testProduct = new Product();
        testProduct.setId(TEST_ID);

        productManager = new ProductManagerImpl();
        productManager.addProduct(testProduct);
    }

    @Test
    public void theTruthTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void productManagerImplShouldBeImplemented() {
        assertNotNull(new ProductManagerImpl());
    }

    @Test
    public void addProductToTheListTest() throws Exception {
        Product justAddedProduct = new Product();
        int sizeBefore = productManager.getAllProducts().size();
        productManager.addProduct(justAddedProduct);
        assertTrue(productManager.getAllProducts().contains(justAddedProduct));
        assertEquals(sizeBefore+1,productManager.getAllProducts().size());
    }

    @Test
    public void getProductByIdShouldReturnProductWithGivenId() {
        assertEquals(testProduct.getId(),productManager.getProductById(TEST_ID).getId());
    }

    @Test
    public void getAllProductsShouldReturnCompleteListOfProducts() throws Exception {
        assertEquals(1,productManager.getAllProducts().size());
        productManager.addProduct(new Product());
        assertEquals(2,productManager.getAllProducts().size());
    }

    @Test
    public void updateProduct() {
        Product productToUpdate = new Product();
        productToUpdate.setId(TEST_ID);
        productToUpdate.setName("Śpiworek");
        productToUpdate.setDescription("Ładny, cieplutki i tani");
        productManager.updateProduct(productToUpdate);
        assertEquals("Śpiworek", productManager.getProductById(TEST_ID).getName());
        assertEquals("Ładny, cieplutki i tani", productManager.getProductById(TEST_ID).getDescription());
    }

    @Test
    public void deleteProduct() {
        assertNotNull(productManager.getProductById(TEST_ID));
        productManager.deleteProduct(TEST_ID);
        assertNull(productManager.getProductById(TEST_ID));
    }

}
