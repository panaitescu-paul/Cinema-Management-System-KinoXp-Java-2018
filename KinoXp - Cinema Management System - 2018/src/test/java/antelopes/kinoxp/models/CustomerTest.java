package antelopes.kinoxp.models;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer = new Customer(1, "Antelope");


    @Test
    public void getName() {
        assertEquals("Antelope", customer.getName());
    }

    @Test
    public void setName() {
        customer.setName("Cheetah");
        assertEquals("Cheetah", customer.getName());
    }

    @Test
    public void getId() {
        assertEquals(1, customer.getId());
    }

    @Test
    public void setId() {
        customer.setId(2);
        assertEquals(2, customer.getId());
    }
}