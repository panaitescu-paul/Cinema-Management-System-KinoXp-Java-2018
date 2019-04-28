package antelopes.kinoxp.models;

public class Seat {

    private int id;
    private String space; // 1A, 1B
    private int room; // 1 or 2
    private int booked; // 0 or 1

    public Seat(int id, String space, int room, int booked) {
        this.id = id;
        this.space = space;
        this.room = room;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int isBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }


}

