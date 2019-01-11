package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import utility.DatabaseConnection;
import view.LoginView;
import view.MainView;
import model_class.*;

public class LoginController {
	private LoginView loginView = new LoginView();
	private AccountDao accountDaoImpl = new AccountDaoImpl();
	
	public void start(){

	int keuze = 1;
	do{
	DatabaseController databaseController = new DatabaseController();
	loginView.PrintMenuHeader();
	loginView.PrintMenuOptions();
	keuze = loginView.RequestInt();
	
	switch (keuze) {
		case 1: CheckAccountByEmail();
		case 2: CreateAccount(); CheckPassword(); break;
		case 3: databaseController.InitDatabase(); break;
		case 4: databaseController.ClearDatabase(); break;
		case 5: MainView mainViewMainView.runView();
		case 0: break;
		default:loginView.InvalidInput(); break;
			}
		}
	while(keuze != 0);
	}
	
	public void CheckAccountByEmail(){
		try {accountDaoImpl.readAccountByEmail(loginView.RequestInputUsername());
		}
		catch(Exception e){}
		loginView.UnknownUsername();
	}
	
	
	public void CheckPassword(){
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
	

