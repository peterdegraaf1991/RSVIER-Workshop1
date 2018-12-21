package dao;

import model_class.Product;

public interface ProductDao {

public void createProduct(Product product);
 
public void readProduct (int id);

public void updateProduct(Product product);

public void deleteProduct(int id);
	
//public void printProduct();
//public void printProduct(int id);		
//public void printProduct(String name);
}
