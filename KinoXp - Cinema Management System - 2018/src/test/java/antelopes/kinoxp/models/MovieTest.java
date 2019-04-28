package antelopes.kinoxp.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    private Movie movie = new Movie(1, "Avatar", "Horror", 18);

    @Test
    public void getName() {
        assertEquals("Avatar", movie.getName());
    }

    @Test
    public void setName() {
        movie.setName("Terminator");
        assertEquals("Terminator", movie.getName());
    }

    @Test
    public void getGenre() {
        assertEquals("Horror", movie.getGenre());
    }

    @Test
    public void setGenre() {
        movie.setGenre("Drama");
        assertEquals("Drama", movie.getGenre());
    }

    @Test
    public void getAgeLimit() {
        assertEquals(18, movie.getAgeLimit());
    }

    @Test
    public void setAgeLimit() {
        movie.setAgeLimit(16);
        assertEquals(16, movie.getAgeLimit());
    }

    @Test
    public void getId() {
        assertEquals(1, movie.getId());
    }

    @Test
    public void setId() {
        movie.setId(2);
        assertEquals(2,movie.getId());
    }
}