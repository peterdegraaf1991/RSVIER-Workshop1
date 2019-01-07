package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.time.ZonedDateTime

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import model_class.Customer;
import model_class.Order;
import model_class.OrderLine;
import model_class.Product;

public class OrderDaoImpl implements OrderDao {

private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	@Override
	public void createOrder(Order order) {
		String query = "INSERT INTO order (id, total_cost, date, customer_id,) VALUES( ?, ?, ?, ?)"; 
	    try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
		         preparedStatement.setInt(1, order.getId()); 
		         preparedStatement.setBigDecimal(2, order.getTotalCost());
		         preparedStatement.setTimestamp(3, Timestamp.from(order.getDate().atZone(ZoneId.of("Europe/Amsterdam")).toInstant()));
		         preparedStatement.setInt(4, order.getCustomer().getId()); 
		         preparedStatement.executeUpdate(); 
		         LOG.info("order: " + order.getId() + " successfully created"); 
	    } 
	    catch (SQLException e) { 
	    	e.printStackTrace(); 
		} 
	}


	@Override
	public void updateOrder(Order order) {
		String query = "UPDATE order SET total_cost = ? , date = ?, customer_id = ?  WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			preparedStatement.setBigDecimal(1, order.getTotalCost());
	        preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getDate())); 
	        preparedStatement.setInt(3, order.getCustomer().getId()); 
	        preparedStatement.executeUpdate(); 
			LOG.info("order with id '" + order.getId()+ "' has been updated");
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

	@Override
	public void deleteOrder(int id) {
		String query = "DELETE FROM order WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
			  preparedStatement.setInt(1, id); 
			  preparedStatement.executeUpdate(); 
			  LOG.info("Order with id: " + id + " successfully removed");
			  } 
		catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}

	@Override
	public void readOrderById(int id) {
		Order order = new Order();
		String query = "SELECT * FROM order WHERE id = ?"; 
		try 
		   (Connection connection = DatabaseConnection.INSTANCE.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, id); 
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			order.setId (resultSet.getInt("id"));
			order.setTotalCost (resultSet.getBigDecimal("total_cost"));
			order.setDate (resultSet.getTimestamp("date").toLocalDateTime());
			
			CustomerDao customerDaoImpl = new CustomerDaoImpl();
			Customer customer = customerDaoImpl.readCustomerById(resultSet.getInt("customer_id"));
			order.setCustomer(customer);
			
			OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();
			OrderLine orderLine = orderLineDaoImpl.readOrderLineById(resultSet.getInt("id"));
			order.setOrderLine(orderLine);
			
			
			ProductDao productDaoImpl = new ProductDaoImpl();
			//uses resultSet from readOrderLine or readProduct?
			Product product = productDaoImpl.readProductById(resultSet.getInt("id"));
			orderLine.setProduct(product); 
			
			
			  private int id;
			  private BigDecimal totalCost;
			  private LocalDateTime date;
			  private Customer customer;
			  private List<OrderLine> orderLines;
			  
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


	@Override
	public void readOrderFromCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	}


