package antelopes.kinoxp.repositories;

import antelopes.kinoxp.models.Customer;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerRepository extends Repository<Customer> {
    public CustomerRepository() {
        super();
    }

    @Override
    public Customer get(int id) {
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE id=?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new Customer(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new LinkedList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM customers");

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customers.add(new Customer(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return customers;
    }

    @Override
    public boolean delete(int id) {
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE id=?");
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean update(Customer object) {
        try{
            preparedStatement = connection.prepareStatement("UPDATE customers SET name=? WHERE id=?");
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getId());

            return preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }

    @Override
    public boolean create(Customer object) {
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO customers(name) VALUES(name)");
            preparedStatement.setString(1, object.getName());

            return preparedStatement.execute();
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return false;
    }
}
