package dao;

import model_class.AddressType;

public interface AddressTypeDao {

	public void createAddressType(AddressType addressType);
	
	public void deleteAddressType(int id);
	
	public void readAddressType(int id);
	
	public void updateAddressType(AddressType addressType);
}
