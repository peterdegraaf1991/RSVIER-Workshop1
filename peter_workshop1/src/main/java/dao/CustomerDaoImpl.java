package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.Customer;

public class CustomerDaoImpl implements CustomerDao {
private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Override
	public void createCustomer(Customer customer) {
		String query = "INSERT INTO customer (id, firstname, middlename, surname) VALUES( ?, ?, ?, ?)"; 
	    try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
		         preparedStatement.setInt(1, customer.getId()); 
		         preparedStatement.setString(2, customer.getFirstname());
		         preparedStatement.setString(3, customer.getMiddlename());
		         preparedStatement.setString(4, customer.getSurname());
		         preparedStatement.executeUpdate(); 
		         LOG.info("Customer: " + customer.getId() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}
	
	@Override
	public void updateCustomer(Customer customer) {
		String query = "UPDATE customer SET firstname = ?, middlename = ?, surname = ? WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			 preparedStatement.setString(1, customer.getFirstname());
	         preparedStatement.setString(2, customer.getMiddlename());
	         preparedStatement.setString(3, customer.getSurname());
			  preparedStatement.executeUpdate(); 
			  LOG.info("customerid: " + customer.getId()+ " has been updated");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

	@Override
	public void deleteCustomer(int id) {
		String query = "DELETE FROM customer WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("Customer with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
	
	@Override
	public void readCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

}
