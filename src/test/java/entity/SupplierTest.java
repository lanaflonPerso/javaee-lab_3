package entity;

import crud.EntityService;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the app
 */
public class SupplierTest {

    private static EntityService es = new EntityService();

    @Test
    public void getAllSuppliersTest() {
        List<Supplier> all = es.getAll(Supplier.class);
        System.out.println(all.size());
        for (Supplier c : all) {
            System.out.println(c);
        }

        assertTrue(all.size() > 0);
    }

    @Test
    public void getSupplierByIdTest() {
        Supplier c = es.getById(1, Supplier.class);
        assertNotNull(c);
    }

    @Test
    public void addSupplierTest() {
        int beforeCount = es.getAll(Supplier.class).size();

        Supplier supplier = new Supplier("NewSupplier", "www.new-company.com", "Some Description");
        es.add(supplier);
        int afterCount = es.getAll(Supplier.class).size();

        assertEquals(beforeCount + 1, afterCount);
        es.delete(supplier);
    }

    @Test
    public void updateSupplierTest() {
        Supplier c = es.getById(2, Supplier.class);
        String newName = "Updated Name";
        String newUrl = "Updated Url";
        c.setName(newName);
        c.setUrl(newUrl);
        es.update(c);
        Supplier c2 = es.getById(2, Supplier.class);
        assertEquals(newName, c2.getName());
        assertEquals(newUrl, c2.getUrl());
    }

    @Test
    public void deleteSupplierTest() {
        Supplier supplier = new Supplier("It'll", "be deleted", "test");
        es.add(supplier);
        int beforeCount = es.getAll(Supplier.class).size();

        es.delete(supplier);
        int afterCount = es.getAll(Supplier.class).size();

        assertEquals(beforeCount-1, afterCount);
    }

    @AfterClass
    public static void closeResources() {
        es.close();
    }

}
