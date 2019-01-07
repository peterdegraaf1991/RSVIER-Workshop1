package model_class;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



public class Order {
  private int id;
  private BigDecimal totalCost;
  private LocalDateTime date;
  private Customer customer;
  private List<OrderLine> orderLines;
  
  public Order(int id, BigDecimal totalCost, LocalDateTime date, Customer customer, List<OrderLine> orderLines) {
	  this.id = id;
	  this.totalCost = totalCost;
	  this.date = date;
	  this.customer = customer;
	  this.orderLines = orderLines;
  }

public Order() {
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public BigDecimal getTotalCost() {
	return totalCost;
}

public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
}

public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public List<OrderLine> getOrderLines() {
	return orderLines;
}

public void setOrderLines(List<OrderLine> orderLines) {
	this.orderLines = orderLines;
}




}
