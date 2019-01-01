package dao;

import model_class.Address;

public interface AddressDao {

	public void createAddress(Address address);
	  
	public void readAddress(int id);
	
	public void updateAddress(Address address);
	
	public void deleteAddress(int id);
}
