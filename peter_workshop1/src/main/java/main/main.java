package main;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.*;
import model_class.*;
import utility.*;

public class main {

public static void main(String[] args) throws SQLException {

// Hoe vindt ik welke resources nog open staan. Ik lees over de 'SpotBugs' maven-plugin?
//		ProductDao productDaoImpl = new ProductDaoImpl();
//		productDaoImpl.readAllProducts();
//		Product PetersPion5 = new Product("PetersPion7", 11, new BigDecimal("6"), 10);
//		Product PetersKoning5 = new Product("PetersKoning7", 12, new BigDecimal("15"), 20);
//		
//		Product PetersPionUpdated = new Product("PetersPionUpdated", 3, new BigDecimal("7"), 10);
//		
//		productDaoImpl.createProduct(PetersPion5);
//		productDaoImpl.createProduct(PetersKoning5);
//		
//		productDaoImpl.updateProduct(PetersPionUpdated);

//		System.out.println(productDaoImpl.readProductById(11).getName());
//		productDaoImpl.readAllProducts();
//		productDaoImpl.printProduct("PetersPion");
//		productDaoImpl.printProduct("PetersPionUpdated");
//		productDaoImpl.printProduct(2);
//		productDaoImpl.printProduct();

//		AccountTypeDaoImpl accountTypeDaoImpl = new AccountTypeDaoImpl();
/		AccountType admin = new AccountType ("admin");
		accountTypeDaoImpl.createAccountType(admin);
	
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		Customer customer = new Customer ("Peter","de","Graaf");
		customerDaoImpl.createCustomer (customer);
		
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		Account accountPeter = new Account (customer,"peterdegraaf1991@hotmail.com","rsvier",1);
//		System.out.println(accountPeter.getCustomer().getId());
//	    System.out.println(accountPeter.getAccountTypeId());

		accountDaoImpl.createAccount(accountPeter);
//		Account accountPeterUpdated = new Account (5,5,"peter2@hotmail.com",null,2);
//		accountDaoImpl.updateAccount(accountPeterUpdated);
//	}	public Account(int id, Customer customer, String email, String password, int accountTypeId)

}
}
