package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.Customer;

public class CustomerDaoImpl implements CustomerDao {
private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Override
	public void createCustomer(Customer customer) {
		String query = "INSERT INTO customer (id, firstname, middlename, surname) VALUES( ?, ?, ?, ?)";
		int generatedId = 0;
	    try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
		         preparedStatement.setInt(1, customer.getId()); 
		         preparedStatement.setString(2, customer.getFirstname());
		         preparedStatement.setString(3, customer.getMiddlename());
		         preparedStatement.setString(4, customer.getSurname());
        		 preparedStatement.executeUpdate(); 
        		 ResultSet rs = preparedStatement.getGeneratedKeys();
        		 if (rs.next()) {
        			 generatedId = rs.getInt(1);
		         LOG.info("Customer with id '" + generatedId + "' successfully created"); 
	    } 
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
	public Customer readCustomerById(int id) {
		Customer customer = new Customer();
		String query = "SELECT * FROM customer WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			customer.setId (resultSet.getInt("id"));
			customer.setFirstname (resultSet.getString("firstname"));
			customer.setMiddlename (resultSet.getString("middlename"));
			customer.setSurname (resultSet.getString("surname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public ArrayList<Customer> readCustomersByLastname(String lastname) {
			ArrayList<Customer> listOfCustomers = new ArrayList<>();
			String query = "SELECT * FROM customer WHERE surname = ?"; 
			try 
			   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)){
				preparedStatement.setString(1, lastname); 
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					Customer customer = new Customer();
					customer.setId (resultSet.getInt("id"));
					customer.setFirstname (resultSet.getString("firstname"));
					customer.setMiddlename (resultSet.getString("middlename"));
					customer.setSurname (resultSet.getString("surname"));
					listOfCustomers.add(customer);
					}
				} 
			catch (SQLException e) {
					e.printStackTrace();
				} 
			return listOfCustomers;
	}

	@Override
	public void deleteCustomer(String lastname) {
		String query = "DELETE FROM customer WHERE surname = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			  preparedStatement.setString(1, lastname); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("Person successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
}

