package antelopes.kinoxp.models;

public class Customer {

    private int id;
    private String customerName;

    public Customer(int id, String name) {
        this.id = id;
        this.customerName = name;
    }

    public String getName() {
        return customerName;
    }

    public void setName(String name) {
        this.customerName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

