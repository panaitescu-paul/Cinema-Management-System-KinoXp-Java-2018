package antelopes.kinoxp.models;

import java.time.LocalDate;

public class Reservation {
    private Movie movie;
    private LocalDate date;
    private String seatNumber;
    private String customerName;
    private int id;

    public Reservation(int id, Movie movie, String date, String seat, Customer customer){
        this.id = id;
        this.movie = movie;
        this.setDate(date);
        this.seatNumber = seat;
        this.customerName = customer.getName();
    }

    public Reservation(int id, Movie movie, LocalDate date, String seat, String customerName) {
        this.id = id;
        this.movie = movie;
        this.date = date;
        this.seatNumber = seat;
        this.customerName = customerName;
    }

    public Reservation(int id, Movie movie, String date, String seat, String customerName) {
        this.id = id;
        this.movie = movie;
        this.setDate(date);
        this.seatNumber = seat;
        this.customerName = customerName;
    }

    public Reservation(Movie movie, LocalDate date, String seat, String customerName) {
        this.movie = movie;
        this.date = date;
        this.seatNumber = seat;
        this.customerName = customerName;
    }

    public Reservation(Movie movie, String date, String seat, String customerName) {
        this.movie = movie;
        this.setDate(date);
        this.seatNumber = seat;
        this.customerName = customerName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String date){
        String[] dateSplit = date.split("-");
        this.date = LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCustomer() {
        return customerName;
    }

    public void setCustomer(String customerName) {
        this.customerName = customerName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
