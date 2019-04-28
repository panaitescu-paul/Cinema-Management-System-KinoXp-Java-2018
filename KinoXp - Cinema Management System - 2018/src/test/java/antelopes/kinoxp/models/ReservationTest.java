package antelopes.kinoxp.models;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReservationTest {

    private Movie movie = new Movie(1, "Avatar", "Horror", 18);
    private Seat seat = new Seat(1, "1A", 1, 1);
    private Customer customer = new Customer(1, "Stefan");
    private Reservation reservation = new Reservation(1, movie, LocalDate.parse("2015-03-03").toString(), "1A", customer);

    @Test
    public void getId() {
        assertEquals(1, reservation.getId());
    }

    @Test
    public void setId() {
        reservation.setId(2);
        assertEquals(2, reservation.getId());
    }

    @Test
    public void getCustomer() {
        assertEquals("Stefan", customer.getName());
    }

    @Test
    public void setCustomer() {
        customer.setName("Dragos");
        assertEquals("Dragos", customer.getName());
    }

    @Test
    public void getSeat() {
        assertEquals(true, seat.isBooked());
    }

    @Test
    public void setSeat() {
        seat.setBooked(0);
        assertEquals(0, seat.isBooked());
    }

    @Test
    public void getDate() {
        assertEquals(LocalDate.parse("2015-03-03"), reservation.getDate());
    }

    @Test
    public void setDate() {
        reservation.setDate(LocalDate.parse("2015-04-04"));
        assertEquals(LocalDate.parse("2015-04-04"), reservation.getDate());

    }

    @Test
    public void getMovieName() {
        assertEquals("Avatar", movie.getName());
    }

}