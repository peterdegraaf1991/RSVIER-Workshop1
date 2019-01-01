package dao;

import model_class.OrderLine;

public interface OrderLineDao {

	public void createOrderLine(OrderLine orderLine);
	
	public void readOrderLine (int id);
	
	public void updateOrderLine(OrderLine orderLine);
	
	public void deleteOrderLine(int id); 
}
