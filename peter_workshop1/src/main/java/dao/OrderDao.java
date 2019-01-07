package dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import model_class.Customer;
import model_class.Order;
import model_class.OrderLine;

public interface OrderDao {

	public void createOrder(Order order);
	
	public void readOrderById (int id);
	public void readOrderFromCustomer(Customer customer);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(int id); 	
}
