package antelopes.kinoxp.models;

public class Schedule {
    private int id;
    private String name;

    public Schedule(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Schedule(String name) {
        this.name = name;
    }
    public Schedule() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
