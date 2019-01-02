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
//		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		
//		Product PetersPion5 = new Product("PetersPion5", 9, new BigDecimal("6"), 10);
//		Product PetersKoning5 = new Product("PetersKoning5", 10, new BigDecimal("15"), 20);
//		
//		Product PetersPionUpdated = new Product("PetersPionUpdated", 3, new BigDecimal("7"), 10);
//		
//		productDaoImpl.createProduct(PetersPion5);
//		productDaoImpl.createProduct(PetersKoning5);
//		
//		productDaoImpl.updateProduct(PetersPionUpdated);

//		productDaoImpl.printProduct("PetersPion");
//		productDaoImpl.printProduct("PetersPionUpdated");
//		productDaoImpl.printProduct(2);
//		productDaoImpl.printProduct();

		AccountTypeDaoImpl accountTypeDaoImpl = new AccountTypeDaoImpl();
		AccountType admin = new AccountType (1, "admin");
		AccountType admin2 = new AccountType (2, "admin2");
		accountTypeDaoImpl.createAccountType(admin);
		accountTypeDaoImpl.createAccountType(admin2);
		
	
//		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
//		Customer customer = new Customer (5,"P","d","G");
//		customerDaoImpl.createCustomer (customer);
		
//		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
//		Account accountPeter = new Account (5,0,"peterdegraaf1991@hotmail.com","rsvier",1);
//		Account accountPeterUpdated = new Account (5,5,"peter2@hotmail.com",null,2);
//		accountDaoImpl.createAccount(accountPeter);
//		accountDaoImpl.updateAccount(accountPeterUpdated);
	}

}
