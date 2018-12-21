package dao;



import model_class.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDaoImpl implements ProductDao {

private static final Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	public void createProduct(Product product) {
		String query = "INSERT INTO product (name, id, price, stock) VALUES( ?, ?, ?, ?)"; 
	    try {
	    	PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
		         preparedStatement.setString(1, product.getName()); 
		         preparedStatement.setInt(2, product.getId());
		         preparedStatement.setBigDecimal(3, product.getPrice()); 
		         preparedStatement.setInt(4, product.getStock()); 
		         preparedStatement.executeUpdate(); 
		         preparedStatement.close();
		         LOG.info("Product: " + product.getName() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}
	@Override
	public void readProduct(int id) {
		// TODO Auto-generated method stub
		
	}
	public void updateProduct(Product product) {
		String query = "UPDATE product SET name = ?, price = ? , stock = ?  WHERE id = ?"; 
		try {
	    	PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
			  preparedStatement.setString(1, product.getName()); 
			  preparedStatement.setBigDecimal(2, product.getPrice()); 
			  preparedStatement.setInt(3, product.getStock());
			  preparedStatement.setInt(4, product.getId());
			  preparedStatement.executeUpdate(); 
			  preparedStatement.close();
			  LOG.info("productid: " + product.getId()+ " has been updated as: " + product.getName());
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void deleteProduct(int id) {
		String query = "DELETE FROM product WHERE id = ?"; 
		try {
			PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  preparedStatement.close();
			  LOG.info("Product with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}



}
//	public void printProduct() {
//	  try {
//			Statement statement = Connect.getConnection().createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from product");
//			
//			
//			ResultSetMetaData metaData = resultSet.getMetaData();
//			for (int i = 1; i <= metaData.getColumnCount(); i++){
//				System.out.printf("%-15s\t", metaData.getColumnName(i));
//			}	
//			System.out.println();
//			
//			while (resultSet.next()) {
//				for (int i = 1; i <= metaData.getColumnCount(); i++)
//					System.out.printf("%-15s\t", resultSet.getObject(i));
//				System.out.println();
//			}
//			statement.close();
//	  } 
//	  catch (SQLException e) { 
//		  e.printStackTrace(); 
//	  } 
//	}
//	public void printProduct(int id) {
//		String query = "SELECT * from product WHERE id = ?"; 
//		try {
//		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
//		  preparedStatement.setInt(1, id); 
//		  ResultSet resultSet = preparedStatement.executeQuery();
//		  
//		  ResultSetMetaData metaData = resultSet.getMetaData();
//			for (int i = 1; i <= metaData.getColumnCount(); i++){
//				System.out.printf("%-15s\t", metaData.getColumnName(i));
//			}	
//			System.out.println();
//			
//			while (resultSet.next()) {
//				for (int i = 1; i <= metaData.getColumnCount(); i++)
//					System.out.printf("%-15s\t", resultSet.getObject(i));
//				System.out.println();
//		}
//			preparedStatement.close();
//		}
//		catch (SQLException e) { 
//			e.printStackTrace(); 
//		} 
//
//	}
//	public void printProduct(String name){
//		String query = "SELECT * from product WHERE name = ?"; 
//		try {
//		PreparedStatement preparedStatement = Connect.getConnection().prepareStatement(query);
//		  preparedStatement.setString(1, name); 
//		  ResultSet resultSet = preparedStatement.executeQuery();
//		  
//		  ResultSetMetaData metaData = resultSet.getMetaData();
//			for (int i = 1; i <= metaData.getColumnCount(); i++){
//				System.out.printf("%-15s\t", metaData.getColumnName(i));
//			}	
//			System.out.println();
//			
//			while (resultSet.next()) {
//				for (int i = 1; i <= metaData.getColumnCount(); i++)
//					System.out.printf("%-15s\t", resultSet.getObject(i));
//				System.out.println();
//		}
//			preparedStatement.close();
//		}
//		catch (SQLException e) { 
//			e.printStackTrace(); 
//		} 
//	}
//}
