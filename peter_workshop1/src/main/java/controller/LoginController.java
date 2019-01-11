package controller;

import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import view.LoginView;
import model_class.*;

public class LoginController {
	private LoginView loginView = new LoginView();
	private AccountDao accountDaoImpl = new AccountDaoImpl();
	
	public void start(){
	CreateAccount();
	
	loginView.LoginHeader();
	CheckAccountByEmail();
	}
	
	public void CheckAccountByEmail(){
		try {accountDaoImpl.readAccountByEmail(loginView.RequestInputUsername());
		}
		catch(Exception e){}
		loginView.UnknownUsername();
	}
	
	
	public void RequestInputPassword(){
	}
	
	public void CreateAccount(){
	loginView.CreateAccountHeader();
	Customer customer = new Customer();
	customer.setFirstname(loginView.RequestInputFirstname());
	customer.setMiddlename(loginView.RequestInputMiddlename());
	customer.setSurname(loginView.RequestInputSurname());
	CustomerDao customerDaoImpl = new CustomerDaoImpl();
	customerDaoImpl.createCustomer(customer);
	
	Account account = new Account();
	account.setEmail(loginView.RequestInputUsername());
	account.setPassword(loginView.RequestInputPassword());
	account.setAccountTypeId(1);
	account.setCustomer (customer);
	accountDaoImpl.createAccount(account);
}
}
	

