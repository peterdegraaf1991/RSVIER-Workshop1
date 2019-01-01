package dao;

import java.time.LocalDateTime;
import java.util.List;

import model_class.Order;

public interface OrderDao {

	public void createOrder(Order order);
	
	public void readOrder (int id);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(int id); 	
	
//	public void getOrder(int customerId); 	
//	public void printOrder(int id);
	
/// how to differentiate between id and customerId (both int's)
}
