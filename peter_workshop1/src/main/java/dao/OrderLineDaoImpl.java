package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.OrderLine;

public class OrderLineDaoImpl implements OrderLineDao {
private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImpl.class);

	@Override
	public void createOrderLine(OrderLine orderLine) {
		String query = "INSERT INTO order_line (id, order_id, product_id, amount) VALUES( ?, ?, ?, ?)"; 
	    try 
	    (PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
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
	public void readOrderLine(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		String query = "UPDATE order_line SET order_id = ?, product_id = ?, amount = ?";
		try
		(PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
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
		(PreparedStatement preparedStatement = DatabaseConnection.INSTANCE.getConnection().prepareStatement(query)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("OrderLine with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

}
