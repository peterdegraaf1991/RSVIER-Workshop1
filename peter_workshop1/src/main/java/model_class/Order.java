package model_class;

import java.math.BigDecimal;
import java.time.LocalDateTime;



public class Order {
  private int id;
  private BigDecimal totalCost;
  private LocalDateTime date;
  private int customerId;
  private int orderStatusId;
  
  public Order(int id, BigDecimal totalCost, LocalDateTime date, int customerId, int orderStatusId) {
	  this.id = id;
	  this.totalCost = totalCost;
	  this.date = date;
	  this.customerId = customerId;
	  this.orderStatusId = orderStatusId;
  }

  public int getId() {
	  return id;
	  }

  public BigDecimal	getTotalCost() {
	  return totalCost;
	  }

  public LocalDateTime getDate() {
	  return date;
	  }

  public int getCustomerId(){
	  return customerId;
	  }

  public int getOrderStatusId() {
	  return orderStatusId;
  }

  public void setDate(LocalDateTime date) {
	  this.date = date;
	  }

  public BigDecimal	calculateTotalCost() {
	  return totalCost;
	  }

  public void setorderStatusId(int orderStatusId) {
	this.orderStatusId = orderStatusId;  
  }
  public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
}
}
