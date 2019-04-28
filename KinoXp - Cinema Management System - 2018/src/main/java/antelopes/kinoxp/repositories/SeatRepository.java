package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Seat;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeatRepository extends Repository<Seat> {
    public SeatRepository() {
        super();
    }

    @Override
    public Seat get(int id) {

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM  seats WHERE  id= ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return  new Seat(
                    resultSet.getInt("id"),
                    resultSet.getString("space"),
                    resultSet.getInt("room"),
                    resultSet.getInt("booked")
            );


        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public Seat get(String space) {

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM seats WHERE space=?");
            preparedStatement.setString(1, space);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Seat(resultSet.getInt("id"), resultSet.getString("space"),
                        resultSet.getInt("room"), resultSet.getInt("booked"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return null;
    }

    @Override
    public List<Seat> getAll() {

        List<Seat> seats = new LinkedList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM seats");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                seats.add(new Seat(resultSet.getInt("id"), resultSet.getString("space"),
                        resultSet.getInt("room"), resultSet.getInt("booked")));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return seats;
    }

    @Override
    public boolean delete(int id) {
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM seats WHERE id=?");
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }

        return false;
    }

    @Override
    public boolean update(Seat object) {
        try{
            preparedStatement = connection.prepareStatement("UPDATE seats SET space=?, room=?, booked=?  WHERE id=?");
            preparedStatement.setString(1, object.getSpace());
            preparedStatement.setInt(2, object.getRoom());
            preparedStatement.setInt(3, object.isBooked());
            preparedStatement.setInt(4, object.getId());

            return preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean create(Seat object) {
        return false;
    }
}
