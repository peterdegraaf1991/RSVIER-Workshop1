package controller;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import view.LoginView;

public class LoginController extends Controller {

	static Account loggedInAccount = new Account();
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
				CheckAccountByEmail(); /* CheckPassword() */
				;
				break;
			case 0:
				keuze = 0;
				break;
			default:
				loginView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	public void CheckAccountByEmail() {
		// This reads the password from the database, which probably isn't the
		// safest thing to do :)
		loggedInAccount = accountDao.readAccountByEmail(loginView
				.RequestInputUsername());

		if (loginView.RequestInputPassword().equals(
				loggedInAccount.getPassword())
				&& loggedInAccount.getId() != 0) {
			loginView.LoginSuccesfull();
			loggedInCustomer = loggedInAccount.getCustomer();

			MainController mainController = new MainController();
			mainController.runController();
		} else
			loginView.IncorrectEmailOrPassword();
		// Controller.newView = true;

	}

}
