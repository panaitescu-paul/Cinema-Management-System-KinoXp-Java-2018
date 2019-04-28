package antelopes.kinoxp.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SeatTest {

    private Seat seat = new Seat(1, "1A", 1, 1);

    @Test
    public void getBooked() {
        assertEquals(1, seat.isBooked());
    }

    @Test
    public void setAvailability() {
        seat.setBooked(0);
        assertEquals(0, seat.isBooked());
    }

    @Test
    public void getRoom() {
        assertEquals(1, seat.getRoom());
    }

    @Test
    public void setRoom() {
        seat.setRoom(2);
        assertEquals(2, seat.getRoom());
    }

    @Test
    public void getSpace() {
        assertEquals("1A", seat.getSpace());
    }

    @Test
    public void setSpace() {
        seat.setSpace("2A");
        assertEquals("2A", seat.getSpace());
    }

    @Test
    public void getId() {
        assertEquals(1, seat.getId());
    }

    @Test
    public void setId() {
        seat.setId(2);
        assertEquals(2, seat.getId());
    }
}