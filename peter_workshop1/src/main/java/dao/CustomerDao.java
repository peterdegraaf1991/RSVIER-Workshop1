package dao;

import java.util.ArrayList;

import model_class.Customer;

public interface CustomerDao {
	  public void createCustomer(Customer customer);
	  
	  public void updateCustomer(Customer customer);

	  public void deleteCustomer(int id);
	 	
	  public Customer readCustomerById(int id);	
	  public ArrayList<Customer> readCustomersByLastname(String lastname);
	   
}
