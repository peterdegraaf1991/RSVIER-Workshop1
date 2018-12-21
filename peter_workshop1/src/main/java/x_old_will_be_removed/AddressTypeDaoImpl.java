package x_old_will_be_removed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.Connect;

public class AddressTypeDaoImpl implements AddressTypeDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(AddressTypeDaoImpl.class);
	
	public void createAddressType(String description) {
		// TODO Auto-generated method stub
		//Auto-increment
		String query = "INSERT INTO addresstype (description) values (?)";
		try {
		  PreparedStatement preparedStatement = Connect.createconnection().prepareStatement(query);
	      preparedStatement.setString(1, description);
		  preparedStatement.executeUpdate(); 
		  LOG.info("Addresstype successfully created"); 
		} 
		catch (SQLException e) { 
		  	e.printStackTrace(); 
		} 
	}

	public void deleteAddressType(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE addresstype where id = ?"; 
	    try {
			  PreparedStatement preparedStatement = Connect.createconnection().prepareStatement(query);
			  preparedStatement.setInt(1, id);
		  preparedStatement.executeUpdate(); 
		  LOG.info("Address type successfully deleted"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

}
