package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Snack;
import antelopes.kinoxp.repositories.Repository;
import antelopes.kinoxp.repositories.util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SnackRepository extends Repository<Snack> {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SnackRepository(){
        try {
            this.connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Snack get(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM  snacks WHERE  id= ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return  new Snack(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            );


        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }

    }


    @Override
    public List<Snack> getAll()  {
        List<Snack> snacks = new LinkedList<>();


        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM  snacks ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                snacks.add(new Snack(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")));


            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return snacks;
    }


    @Override
    public boolean delete(int id) {

        try{

            preparedStatement = connection.prepareStatement("DELETE FROM snacks WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
          /*  if(preparedStatement.executeUpdate() > 0){*/
                return true;
            /*}*/
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }
/*
    public boolean delete(Snack object) {
        try {
            PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM snacks WHERE id= ?") ;
            preparedStatement.setInt(1,object.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }*/




    @Override
    public boolean update(Snack object) {

        try {
            preparedStatement = connection.prepareStatement("UPDATE snacks SET name=?, price=? WHERE id=?");
            preparedStatement.setString(1,object.getName());
            preparedStatement.setInt(2,object.getPrice());
            preparedStatement.setInt(3,object.getId());
            preparedStatement.execute();

            return true;


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(Snack object) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO snacks(id,name,price) VALUES(?,?,?)");
            preparedStatement.setInt(1,object.getId());
            preparedStatement.setString(2,object.getName());
            preparedStatement.setInt(3,object.getPrice());
            preparedStatement.execute();

            return true;


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}
