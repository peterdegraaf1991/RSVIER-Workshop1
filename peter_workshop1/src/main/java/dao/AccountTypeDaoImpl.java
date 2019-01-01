package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import model_class.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;


public class AccountTypeDaoImpl implements AccountTypeDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountTypeDaoImpl.class);
	
	@Override
	public void createAccountType(AccountType accountType) {
		String query = "INSERT INTO account_type (id, description) VALUES(?, ?)";
	    try 
	    (PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
		         preparedStatement.setInt(1, accountType.getAccountType());  
		         preparedStatement.setString(2, accountType.getDescription());
		         preparedStatement.executeUpdate(); 
		         LOG.info("Accounttype: " + accountType.getDescription() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	@Override
	public void readAccountType(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateAccountType(AccountType accountType) {
		String query = "UPDATE account_type SET description = ? WHERE id = ?"; 
		try 
	    (PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){ 
			  preparedStatement.setString(1, accountType.getDescription()); 
			  preparedStatement.setInt(2, accountType.getAccountType()); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("AccountTypeId: " + accountType.getAccountType() + "now has description: " + accountType.getDescription());
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
	
	@Override
	public void deleteAccountType(int id) {
		String query = "DELETE FROM account_type WHERE id = ?"; 
		try 
		(PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("AccountId: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
}

//	public void printAccountType() {
//		  try {
//				Statement statement = Connect.getConnection().createStatement();
//				ResultSet resultSet = statement.executeQuery("SELECT id, description FROM accounttype");
//				ResultSetMetaData metaData = resultSet.getMetaData();
//				
//				for (int i = 1; i <= metaData.getColumnCount(); i++){
//					System.out.printf("%-12s\t", metaData.getColumnName(i));
//				}	
//				System.out.println();
//				
//				while (resultSet.next()) {
//					for (int i = 1; i <= metaData.getColumnCount(); i++)
//						System.out.printf("%-12s\t", resultSet.getObject(i));
//					System.out.println();
//				}
//				statement.close();
//		  } 
//		  catch (SQLException e) { 
//			  e.printStackTrace(); 
//		  } 
//		}
//	public void printAccountType(int id) {
//				String query = "SELECT id, description FROM accounttype WHERE id = ?"; 
//				try {
//				PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
//				  preparedStatement.setInt(1, id); 
//				  ResultSet resultSet = preparedStatement.executeQuery();
//				  
//				  ResultSetMetaData metaData = resultSet.getMetaData();
//					for (int i = 1; i <= metaData.getColumnCount(); i++){
//						System.out.printf("%-20s\t", metaData.getColumnName(i));
//					}	
//					System.out.println();
//					
//					while (resultSet.next()) {
//						for (int i = 1; i <= metaData.getColumnCount(); i++)
//							System.out.printf("%-20s\t", resultSet.getObject(i));
//						System.out.println();
//				}
//					preparedStatement.close();
//				}
//				catch (SQLException e) { 
//					e.printStackTrace(); 
//				} 
//			}
//		
