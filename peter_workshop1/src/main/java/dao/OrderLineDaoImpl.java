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
		String query = "INSERT INTO order_line (id, product_id, amount) VALUES( ?, ?, ?)"; 
	    try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
		         preparedStatement.setInt(1, orderLine.getId()); 
		         preparedStatement.setInt(2, orderLine.getProduct().getId()); 
		         preparedStatement.setInt(3, orderLine.getAmount()); 
		         preparedStatement.executeUpdate(); 
		         LOG.info("OrderLine: " + orderLine.getId() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}
	
	@Override
	public OrderLine readOrderLineById(int id) {
		OrderLine orderLine = new OrderLine();
		String query = "SELECT * FROM order_line WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			orderLine.setId (resultSet.getInt("id"));
			orderLine.setAmount (resultSet.getInt("amount"));
			ProductDao productDaoImpl = new ProductDaoImpl();
			//uses resultSet from readOrderLine or readProduct?
			Product product = productDaoImpl.readProductById(resultSet.getInt("id"));
			orderLine.setProduct(product); 
			LOG.info("ProductLine with id '" + id + "' read");
			}
		
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return orderLine;
	}
	
	public List<OrderLine> readAllOrderLines() {
		List<OrderLine> orderLineList = new ArrayList<>();
		String query = "SELECT * FROM order_line"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
			OrderLine orderLine = new OrderLine();
			orderLine.setId (resultSet.getInt("id"));
			orderLine.setAmount (resultSet.getInt("amount"));
			ProductDao productDaoImpl = new ProductDaoImpl();
			//uses resultSet from readOrderLine or readProduct?
			Product product = productDaoImpl.readProductById(resultSet.getInt("id"));
			orderLine.setProduct(product); 
			orderLineList.add(orderLine);
			}
			LOG.info("'" + orderLineList.size() + "' OrderLines read");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		return orderLineList;
	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		String query = "UPDATE order_line SET product_id = ?, amount = ?";
		try
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
	         preparedStatement.setInt(1, orderLine.getProduct().getId()); 
	         preparedStatement.setInt(2, orderLine.getAmount()); 
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
