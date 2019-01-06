package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.OrderLine;
import model_class.Product;

public class OrderLineDaoImpl implements OrderLineDao {
private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImpl.class);

	@Override
	public void createOrderLine(OrderLine orderLine) {
		String query = "INSERT INTO order_line (id, order_id, product_id, amount) VALUES( ?, ?, ?, ?)"; 
	    try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
		         preparedStatement.setInt(1, orderLine.getId()); 
		         preparedStatement.setInt(2, orderLine.getOrderId());
		         preparedStatement.setInt(3, orderLine.getProductId()); 
		         preparedStatement.setInt(4, orderLine.getAmount()); 
		         preparedStatement.executeUpdate(); 
		         LOG.info("OrderLine: " + orderLine.getId() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}
	
	@Override
	public Product readOrderLineById(int id) {
		OrderLine orderLine = new Product();
		String query = "SELECT * FROM product_line WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			orderLine.setId (resultSet.getInt("id"));
			orderLine.setStock( resultSet.getInt("stock"));
			orderLine.setPrice (resultSet.getBigDecimal("price"));
			LOG.info("ProudctInfo from product with id '" + id + "' read");
			}

		private final int id;
		private final int orderId;
		private final int productId;
		private int amount;
		
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return product;
	}
	
	public List<Product> readAllProductLists() {
		List<Product> productList = new ArrayList<>();
		String query = "SELECT * FROM product"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
			Product product = new Product();
			product.setName(resultSet.getString("name"));
			product.setId (resultSet.getInt("id"));
			product.setStock( resultSet.getInt("stock"));
			product.setPrice (resultSet.getBigDecimal("price"));
			productList.add(product);
			}
			LOG.info("ProudctInfo of '" + productList.size() + "' products read");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return productList;
	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		String query = "UPDATE order_line SET order_id = ?, product_id = ?, amount = ?";
		try
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
	         preparedStatement.setInt(1, orderLine.getOrderId());
	         preparedStatement.setInt(2, orderLine.getProductId()); 
	         preparedStatement.setInt(3, orderLine.getAmount()); 
	         preparedStatement.executeUpdate();
	         LOG.info("OrderLineId " + orderLine.getId() + " successfully updated");
	         }
		catch (SQLException e) {
			e.printStackTrace();
		}
		}

	@Override
	public void deleteOrderLine(int id) {
		String query = "DELETE FROM order_line WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("OrderLine with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

}
