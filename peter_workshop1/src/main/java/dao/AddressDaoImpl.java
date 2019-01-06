package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.Address;


public class AddressDaoImpl implements AddressDao{
private static final Logger LOG = LoggerFactory.getLogger(AddressDaoImpl.class);

	@Override
	public void createAddress(Address address) {
			String query = "INSERT INTO address (id, customer_id, house_number, address_type_id, street, house_extension, zip_code, city) VALUES( ?, ?, ?, ?, ?)"; 
		    try 
			   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			         preparedStatement.setInt(1, address.getId()); 
			         preparedStatement.setInt(2, address.getCustomerId());
			         preparedStatement.setInt(3, address.getHouseNumber()); 
			         preparedStatement.setInt(4, address.getAddressTypeId()); 
			         preparedStatement.setString(5, address.getStreet()); 
			         preparedStatement.setString(6, address.getHouseExtension());     
			         preparedStatement.setString(7, address.getZipCode());  
			         preparedStatement.setString(8, address.getCity());  
			         preparedStatement.executeUpdate(); 
			         LOG.info("AddressId " + address.getId() + " successfully created"); 
		    } 
		    catch (SQLException e) { 
		    	e.printStackTrace(); 
			} 
		}

	@Override
	public void readAddress(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAddress(Address address) {
		String query = "UPDATE address SET customer_id = ?, house_number = ?, address_type_id = ?, street = ?, house_extension = ?, zip_code = ?, city = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
	         preparedStatement.setInt(1, address.getCustomerId());
	         preparedStatement.setInt(2, address.getHouseNumber()); 
	         preparedStatement.setInt(3, address.getAddressTypeId()); 
	         preparedStatement.setString(4, address.getStreet()); 
	         preparedStatement.setString(5, address.getHouseExtension());     
	         preparedStatement.setString(6, address.getZipCode());  
	         preparedStatement.setString(7, address.getCity()); 
			 preparedStatement.executeUpdate(); 
			 LOG.info("addressid: " + address.getId()+ " has been updated.");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

	@Override
	public void deleteAddress(int id) {
		String query = "DELETE FROM address WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("Address with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

}