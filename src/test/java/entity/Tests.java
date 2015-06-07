package entity;

import crud.EntityService;
import org.junit.Test;

import java.util.List;

/**
 * Tests for the app
 */
public class Tests {
    @Test
    public void foo() {
        Customer customer = new Customer("User");
        EntityService es = new EntityService();

        List<Customer> all = es.getAll(Customer.class);
        System.out.println(all.size());
        for (Customer c : all) {
            System.out.println(c);
        }

//        es.add(customer);
    }
}
