package main;

import java.math.BigDecimal;

import dao.*;
import model_class.*;

public class main {

public static void main(String[] args) {

		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		
		Product PetersPion = new Product("PetersPion", 1, new BigDecimal("6"), 10);
		Product PetersKoning = new Product("PetersKoning", 2, new BigDecimal("15"), 20);
		
		Product PetersPionUpdated = new Product("PetersPionUpdated", 3, new BigDecimal("7"), 10);
		
		productDaoImpl.createProduct(PetersPion);
		productDaoImpl.createProduct(PetersKoning);
		
		productDaoImpl.updateProduct(PetersPionUpdated);

//		productDaoImpl.printProduct("PetersPion");
//		productDaoImpl.printProduct("PetersPionUpdated");
//		productDaoImpl.printProduct(2);
//		productDaoImpl.printProduct();

		AccountTypeDaoImpl accountTypeDaoImpl = new AccountTypeDaoImpl();
		AccountType admin = new AccountType (1, "admin");
		accountTypeDaoImpl.createAccountType(admin);
		
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		Account accountPeter = new Account (0,0,"peterdegraaf1991@hotmail.com","rsvier",1);
		Account accountPeterUpdated = new Account (1,1,"peterdegraaf0302@gmail.com", "rsvier", 1);
		accountDaoImpl.createAccount(accountPeter);
		accountDaoImpl.updateAccount(accountPeterUpdated);
//		accountDaoImpl.printAccount();
	}

}
