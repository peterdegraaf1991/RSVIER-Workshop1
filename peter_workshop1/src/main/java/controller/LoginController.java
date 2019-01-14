package controller;


import java.util.concurrent.TimeUnit;

import model_class.Account;
import dao.AccountDao;
import dao.AccountDaoImpl;
import view.LoginView;


public class LoginController extends Controller {
	
	Account userAccount = new Account();
	private LoginView loginView = new LoginView();
	AccountDao accountDao = new AccountDaoImpl();
	
	public void runController() {
		
	DatabaseController databaseController = new DatabaseController();
	AccountController accountController = new AccountController();
	
	int keuze = 1;
	do{
		if (PrintControl.newView == true){
				loginView.ClearTerminal();
				loginView.PrintMenuHeader();
				loginView.PrintMenuOptions();
			}
	keuze = loginView.RequestMenuOption();
	switch (keuze) {
		case 1: 
		CheckAccountByEmail(); CheckPassword(); break;
		case 2: databaseController.InitDatabase(); break;
		case 3: databaseController.ClearDatabase(); break;
		case 0: keuze = 0; break;
		default:loginView.InvalidInput(); break;
			}
		}
	while(keuze != 0);
	}
	
	public void CheckAccountByEmail(){
		userAccount = accountDao.readAccountByEmail(loginView.RequestInputUsername());
		System.out.println(userAccount);
	}
	
	public void CheckPassword(){
	if (loginView.RequestInputPassword().equals(userAccount.getPassword())){
		loginView.LoginSuccesfull();

		MainController mainController = new MainController();
		mainController.runController();
	}
	else
		loginView.IncorrectPassword();
	
	}
	
}
	

