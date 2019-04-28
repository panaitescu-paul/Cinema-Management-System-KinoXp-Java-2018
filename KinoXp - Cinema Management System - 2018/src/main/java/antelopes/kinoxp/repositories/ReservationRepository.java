package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Customer;
import antelopes.kinoxp.models.Movie;
import antelopes.kinoxp.models.Reservation;
import antelopes.kinoxp.models.Seat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ReservationRepository extends Repository<Reservation> {
    private SeatRepository seatRepository = new SeatRepository();
    public ReservationRepository() {
        super();
    }

    @Override
    public Reservation get(int id) {
        try{
            preparedStatement = connection.prepareStatement("SELECT reservations.id, reservations.seat, reservations.date, reservations.customer, " +
                    "movies.id, movies.name, movies.genre, movies.ageLimit INNER JOIN movies ON reservations.movie_id=movies.id WHERE reservations.id=?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Movie movie = new Movie(
                        resultSet.getInt("movies.id"),
                        resultSet.getString("movies.name"),
                        resultSet.getString("movies.genre"),
                        resultSet.getInt("movies.ageLimit"));
                return new Reservation(
                        resultSet.getInt("reservations.id"),
                        movie,
                        resultSet.getString("reservations.date"),
                        resultSet.getString("seats.space"),
                        resultSet.getString("reservations.customer")
                );
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new LinkedList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT reservations.id, reservations.seat, reservations.date, reservations.customer, " +
                    "movies.id, movies.name, movies.genre, movies.ageLimit INNER JOIN movies ON reservations.movie_id=movies.id");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Movie movie = new Movie(
                        resultSet.getInt("movies.id"),
                        resultSet.getString("movies.name"),
                        resultSet.getString("movies.genre"),
                        resultSet.getInt("movies.ageLimit"));
                reservations.add(new Reservation(
                            resultSet.getInt("reservations.id"),
                            movie,
                            resultSet.getString("reservations.date"),
                            resultSet.getString("seats.space"),
                            resultSet.getString("reservations.customer")
                    ));

            }
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return reservations;
    }

    @Override
    public boolean delete(int id) {
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM reservations WHERE id=?");
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean update(Reservation object) {
        try{
            preparedStatement = connection.prepareStatement("UPDATE reservations SET movie_id=?, seat=?, date=? WHERE id=?");
            preparedStatement.setInt(1, object.getMovie().getId());
            preparedStatement.setString(2, object.getSeatNumber());
            preparedStatement.setString(3, object.getDate().toString());
            preparedStatement.setInt(4, object.getId());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean create(Reservation object) {
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO reservations(movie_id, seat, date, customer) " +
                    "VALUES(?,?,?,?)");
            preparedStatement.setInt(1, object.getMovie().getId());
            preparedStatement.setString(2, object.getSeatNumber());
            preparedStatement.setString(3, object.getDate().toString());
            preparedStatement.setString(4, object.getCustomer());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    // For the bright and lovely future chickas
    // Do not use
    // Police may be involved in case of unauthorized use
    // @Author Dragos'tea'm
    public List<String> getBookedSeats(LocalDate date){
        List<String> seats = new LinkedList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT seat FROM reservations WHERE date=?");
            preparedStatement.setString(1, date.toString());

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                seats.add(resultSet.getString("seat"));
            }
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }

        return seats;
    }
}
