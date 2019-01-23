package dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model_class.Customer;
import model_class.Order;
import model_class.OrderLine;

public interface OrderDao {

	public int createOrder(Order order);

	public Order readOrderById(int id);

	public List<Order> readOrdersOfCustomerId(int customer_id);

	public List<Integer> readCustomerIdsWithOrder();

	public void updateOrder(Order order);

	public int deleteOrder(int id);

	public List<Order> readAllOrders();

}
