package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Schedule;
import antelopes.kinoxp.models.Snack;
import antelopes.kinoxp.repositories.Repository;
import antelopes.kinoxp.repositories.util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ScheduleRepository extends Repository<Schedule> {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ScheduleRepository(){
        try {
            this.connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Schedule get(int id) {
        return  null;
//        try {
//            preparedStatement = connection.prepareStatement("SELECT  * FROM  snacks WHERE  id= ?");
//            preparedStatement.setInt(1,id);
//            resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//
//            return  new Snack(
//                    resultSet.getInt("id"),
//                    resultSet.getString("name"),
//                    resultSet.getInt("price")
//            );
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return  null;
//        }




    }

    @Override
    public List<Schedule> getAll()  {
        List<Schedule> ScheduleList = new LinkedList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM  schedule ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ScheduleList.add(new Schedule(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ScheduleList;
    }

    @Override
    public boolean delete(int id) {

        return false;

//        try{
//
//            preparedStatement = connection.prepareStatement("DELETE FROM snacks WHERE id=?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.execute();
//            /*  if(preparedStatement.executeUpdate() > 0){*/
//            return true;
//            /*}*/
//        }catch (SQLException ex){
//            System.out.println(ex.getSQLState());
//        }
//        return false;

    }


    @Override
    public boolean update(Schedule object) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE schedule SET name = ? WHERE id= ? ");
            preparedStatement.setString(1,object.getName());
            preparedStatement.setInt(2,object.getId());
            preparedStatement.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean create(Schedule object) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO schedule(id,name) VALUES(?,?)");
            preparedStatement.setInt(1,object.getId());
            preparedStatement.setString(2,object.getName());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
