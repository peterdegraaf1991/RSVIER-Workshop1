package controller;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import view.LoginView;
import utility.Hashing;
import utility.Hashing.CannotPerformOperationException;
import utility.Hashing.InvalidHashException;

public class LoginController extends Controller {

	public static Account loggedInAccount = new Account();
	static Customer loggedInCustomer = new Customer();
	private LoginView loginView = new LoginView();
	AccountDao accountDao = new AccountDaoImpl();

	public void runController() {

		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				loginView.ClearTerminal();
				loginView.PrintMenuHeader();
				loginView.PrintMenuOptions();
				Controller.newView = false;
			}
			keuze = loginView.RequestMenuOption();
			switch (keuze) {
			case 1:
				CheckAccountByEmail();
				;
				break;
			case 0:
				loginView.logoutTimer();
				System.exit(0);
				break;
			default:
				loginView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	public void CheckAccountByEmail() {
		loggedInAccount = accountDao.readAccountByEmail(loginView
				.RequestInputUsername());
		int accountId = loggedInAccount.getId();
		if (loggedInAccount.getId() != 0) {
			String hash = accountDao.readHash(accountId);
			try {
				if (Hashing.verifyPassword(loginView.RequestInputPassword(), hash) == true) {
					loginView.LoginSuccesfull();
					loggedInCustomer = loggedInAccount.getCustomer();

					MainController mainController = new MainController();
					mainController.runController();
				} else {
					loginView.IncorrectEmailOrPassword();
					requestNewMenu();
				}
			} catch (CannotPerformOperationException | InvalidHashException e) {
				e.printStackTrace();
			}
		}
		else {
			loginView.IncorrectEmailOrPassword();
			requestNewMenu();
		}
					
	}
}
