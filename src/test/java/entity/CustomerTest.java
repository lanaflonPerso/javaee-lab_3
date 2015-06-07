package entity;

import crud.EntityService;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the app
 */
public class CustomerTest {

    private static EntityService es = new EntityService();

    @Test
    public void getAllCustomersTest() {
        List<Customer> all = es.getAll(Customer.class);
        System.out.println(all.size());
        for (Customer c : all) {
            System.out.println(c);
        }

        assertTrue(all.size() > 0);
    }

    @Test
    public void getCustomerByIdTest() {
        Customer c = es.getById(1, Customer.class);
        assertNotNull(c);
    }

    @Test
    public void addCustomerTest() {
        int beforeCount = es.getAll(Customer.class).size();

        Customer customer = new Customer("Andriy Makarenko");
        es.add(customer);
        int afterCount = es.getAll(Customer.class).size();

        assertEquals(beforeCount + 1, afterCount);
        es.delete(customer);
    }

    @Test
    public void updateCustomerTest() {
        Customer c = es.getById(1, Customer.class);
        String newName = "Updated Name";
        c.setName(newName);
        es.update(c);
        Customer c2 = es.getById(1, Customer.class);
        assertEquals(newName, c2.getName());
    }

    @Test
    public void deleteCustomerTest() {
        Customer customer = new Customer("He'll be Deleted");
        es.add(customer);
        int beforeCount = es.getAll(Customer.class).size();

        es.delete(customer);
        int afterCount = es.getAll(Customer.class).size();

        assertEquals(beforeCount-1, afterCount);
    }

    @AfterClass
    public static void closeResources() {
        es.close();
    }

}
