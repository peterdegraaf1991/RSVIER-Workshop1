package controller;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import dao.AccountDao;
import dao.AccountDaoImpl;
import view.LoginView;
import model_class.*;
import java.lang.*;


public class LoginController {
	private LoginView loginView = new LoginView();
	private AccountDao accountDaoImpl = new AccountDaoImpl();
	
	
	public void start(){
	loginView.printMessage();
	loginView.PrintOptions();
	CheckAccountByEmail();
	}
	
public Account CheckAccountByEmail(){

		Account account = accountDaoImpl.readAccountByEmail(loginView.RequestInputUsername());
		if(account instanceof Account == false){
			loginView.UnknownUsername();
			start();
		}	
		return account;
		
	}
	public void RequestInputPassword(){
		
	}
}
	

