package entity;

import crud.EntityService;
import org.junit.AfterClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the app
 */
public class ProductTest {

    private static EntityService es = new EntityService();

    @Test
    public void addSupplierWithNewSupplierAndCategoryTest() {
        int beforeCount = es.getAll(Product.class).size();

        Supplier supplier = new Supplier("SomeCompany333", "www.some-company.com", "Some Description");
        Category category = new Category("333", "Monitors, laptops, computers");
        es.add(supplier);
        es.add(category);

        Product product = new Product(7634, "Laptop Lenovo Z580", (short)4, new BigDecimal(7600), new Date(), "Lenovo", category, supplier);
        product.setAvailableNumber((short)16);

        es.add(product);

        int afterCount = es.getAll(Product.class).size();
        assertEquals(beforeCount + 1, afterCount);

        es.delete(product);
        es.delete(supplier);
        es.delete(category);
    }

    @Test
    public void addSupplierWithAlreadyExistingSupplierAndCategoryTest() {
        int beforeCount = es.getAll(Product.class).size();

        Supplier supplier = es.getById(7, Supplier.class);
        Category category = es.getById(10, Category.class);

        Product product = new Product(7634, "Example Product", (short)4, new BigDecimal(3600), new Date(), "LG", category, supplier);
        product.setAvailableNumber((short)16);

        es.add(product);

        int afterCount = es.getAll(Product.class).size();
        assertEquals(beforeCount + 1, afterCount);
        es.delete(product);
    }

    @Test
    public void getAllProductsTest() {
        List<Product> all = es.getAll(Product.class);
        System.out.println(all.size());
        for (Product c : all) {
            System.out.println(c);
        }

        assertTrue(all.size() > 0);
    }

    @Test
    public void getProductByIdTest() {
        Product c = es.getById(1, Product.class);
        assertNotNull(c);
    }

    @Test
    public void updateProductTest() {
        Product c = es.getById(1, Product.class);

        int oldCode = c.getCode();
        int newCode = 666;

        c.setCode(newCode);
        es.update(c);

        Product c2 = es.getById(1, Product.class);
        assertEquals(newCode, c2.getCode());

        // switch it to the previous state
        c.setCode(oldCode);
        es.update(c);
    }


    @Test
    public void deleteProductTest() {
        int beforeCount = es.getAll(Product.class).size();

        Supplier supplier = es.getById(7, Supplier.class);
        Category category = es.getById(10, Category.class);

        Product product = new Product(7634, "Example Product", (short)4, new BigDecimal(3600), new Date(), "LG", category, supplier);
        product.setAvailableNumber((short)16);

        es.add(product);

        int afterCount = es.getAll(Product.class).size();
        assertEquals(beforeCount+1, afterCount);

        beforeCount = afterCount;
        es.delete(product);
        afterCount = es.getAll(Product.class).size();

        assertEquals(beforeCount-1, afterCount);
    }

    @AfterClass
    public static void closeResources() {
        es.close();
    }
}
