package antelopes.kinoxp.models;

public class Snack {
    private String name;
    private int id, price;

    public Snack(){

    }

    public Snack(int id, String name, int price){
        this.id=id;
        this.name=name;
        this.price=price;
    }
    public Snack(String name, int price){
        this.name=name;
        this.price=price;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
