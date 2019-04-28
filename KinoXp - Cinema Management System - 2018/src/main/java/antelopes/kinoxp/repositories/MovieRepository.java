package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Movie;
import antelopes.kinoxp.repositories.util.Database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MovieRepository extends Repository<Movie> {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MovieRepository(){
        try {
            this.connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Movie get(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM  movies WHERE  id= ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return  new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("genre"),
                    resultSet.getInt("ageLimit")
            );


        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }

    }

    @Override
    public List<Movie> getAll()  {


        List<Movie> movies = new LinkedList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM  movies ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                movies.add(new Movie(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getInt("ageLimit")));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return movies;

    }

    @Override
    public boolean delete(int id) {
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM movies WHERE id=?");
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
    public boolean update(Movie object) {

        try {
            preparedStatement = connection.prepareStatement("UPDATE movies SET name= ? , genre = ? , ageLimit= ? WHERE id= ? ");
            preparedStatement.setString(1,object.getName());
            preparedStatement.setString(2,object.getGenre());
            preparedStatement.setInt(3,object.getAgeLimit());
            preparedStatement.setInt(4,object.getId());
            preparedStatement.execute();

            return true;


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean create(Movie object) {

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO movies(id,name,genre,ageLimit) VALUES(?,?,?,?)");
            preparedStatement.setInt(1,object.getId());
            preparedStatement.setString(2,object.getName());
            preparedStatement.setString(3,object.getGenre());
            preparedStatement.setInt(4,object.getAgeLimit());
            preparedStatement.execute();

            return true;


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}