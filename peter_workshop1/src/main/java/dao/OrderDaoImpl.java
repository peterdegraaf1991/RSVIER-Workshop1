package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.Order;

public class OrderDaoImpl implements OrderDao {

private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	@Override
	public void createOrder(Order order) {
		String query = "INSERT INTO order (id, total_cost, date, customer_id, order_status_id) VALUES( ?, ?, ?, ?, ?)"; 
	    try 
	    (PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
		         preparedStatement.setInt(1, order.getId()); 
		         preparedStatement.setBigDecimal(2, order.getTotalCost());
		         preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getDate())); 
		         preparedStatement.setInt(4, order.getCustomerId()); 
		         preparedStatement.setInt(4, order.getOrderStatusId()); 
		         preparedStatement.executeUpdate(); 
		         LOG.info("order: " + order.getId() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}

	@Override
	public void readOrder(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateOrder(Order order) {
		String query = "UPDATE order SET total_cost = ? , date = ?, customer_id = ?, order_status_id = ?  WHERE id = ?"; 
		try 
	    (PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
			preparedStatement.setBigDecimal(1, order.getTotalCost());
	        preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getDate())); 
	        preparedStatement.setInt(3, order.getCustomerId()); 
	        preparedStatement.setInt(4, order.getOrderStatusId()); 
	        preparedStatement.executeUpdate(); 
			LOG.info("orderid: " + order.getId()+ " has been updated");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

	@Override
	public void deleteOrder(int id) {
		String query = "DELETE FROM order WHERE id = ?"; 
		try 
		(PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("Order with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
	}


