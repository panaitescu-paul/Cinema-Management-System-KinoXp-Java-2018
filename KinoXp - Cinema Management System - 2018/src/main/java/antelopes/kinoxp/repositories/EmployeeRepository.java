package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Employee;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeRepository extends Repository<Employee> {

    public EmployeeRepository(){
        super();
    }

    @Override
    public Employee get(int id) {
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id=?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Employee(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return null;
    }

    public Employee get(String username) {
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE username=?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Employee(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new LinkedList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM employees");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                employees.add(new Employee(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name")));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return employees;
    }

    @Override
    public boolean delete(int id) {
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean update(Employee object) {
        try{
            preparedStatement = connection.prepareStatement("UPDATE employees SET name=?, password=?, username=? WHERE id=?");
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getUsername());
            preparedStatement.setInt(4, object.getId());

            if(preparedStatement.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean create(Employee object) {
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO employees(name, username, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getUsername());
            preparedStatement.setString(3, object.getPassword());

            if(preparedStatement.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }
}
