package x_old_will_be_removed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model_class.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerDaoImpl implements CustomerDao {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	public void createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO customer (id, firstname, middlename, surname, accountId) VALUES( ?, ?, ?, ?, ?)"; 
	    try {
		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pb_workshop1?serverTimezone=Europe/Amsterdam","root","rsvier");
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
	      preparedStatement.setInt(1, customer.getId());
		  preparedStatement.setString(2, customer.getFirstname());
		  preparedStatement.setString(3, customer.getMiddlename());
		  preparedStatement.setString(4, customer.getSurname());
          preparedStatement.setInt(5, customer.getAccountId()); 
		  preparedStatement.executeUpdate(); 
		  LOG.info("Customer successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String query = "UPDATE customer set firstname = ?, middlename = ?, surname = ?, accountId = ? WHERE id = ?)"; 
	    try {
		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pb_workshop1?serverTimezone=Europe/Amsterdam","root","rsvier");
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, customer.getFirstname());
		  preparedStatement.setString(2, customer.getMiddlename());
		  preparedStatement.setString(3, customer.getSurname());
          preparedStatement.setInt(4, customer.getAccountId()); 
	      preparedStatement.setInt(5, customer.getId());
          preparedStatement.executeUpdate(); 
		  LOG.info("Customer successfully updated"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String query = "DELETE customer WHERE id = ?)"; 
	    try {
		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pb_workshop1?serverTimezone=Europe/Amsterdam","root","rsvier");
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
	      preparedStatement.setInt(1, customer.getId());
          preparedStatement.executeUpdate(); 
		  LOG.info("Customer successfully deleted"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	public void getCustomerBySurname(String surname) {
		// TODO Auto-generated method stub
		String query = "SELECT DISTINCT id, firstname, middlename, surname, accoutId FROM customer WHERE surname = ?)"; 
	    try {
		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pb_workshop1?serverTimezone=Europe/Amsterdam","root","rsvier");
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(3, surname);
          preparedStatement.executeUpdate(); 
		  LOG.info("Customers successfully retrieved"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	public void getCustomerById(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT id, firstname, middlename, surname, accoutId FROM customer WHERE id = ?)"; 
	    try {
		  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pb_workshop1?serverTimezone=Europe/Amsterdam","root","rsvier");
		  PreparedStatement preparedStatement = connection.prepareStatement(query);
	      preparedStatement.setInt(1, id);
          preparedStatement.executeUpdate(); 
		  LOG.info("Customer successfully retrieved"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

}
