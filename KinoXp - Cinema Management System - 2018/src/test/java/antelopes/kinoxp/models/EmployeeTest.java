package antelopes.kinoxp.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    private Employee employee = new Employee(1, "userName", "passWord", "Employee1");

    @Test
    public void getId() {
        assertEquals(1, employee.getId());
    }

    @Test
    public void setI() {
        employee.setId(2);
        assertEquals(2,employee.getId());
    }

    @Test
    public void getUsername() {
        assertEquals("userName", employee.getUsername());
    }

    @Test
    public void setUsername() {
        employee.setUsername("anotherOne");
        assertEquals("anotherOne", employee.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("passWord", employee.getPassword());
    }

    @Test
    public void setPassword() {
        employee.setPassword("secondPassword");
        assertEquals("secondPassword", employee.getPassword());
    }

    @Test
    public void getName() {
        assertEquals("Employee1", employee.getName());
    }

    @Test
    public void setName() {
        employee.setName("Employee2");
        assertEquals("Employee2", employee.getName());
    }

}