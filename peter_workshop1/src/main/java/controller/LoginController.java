package controller;

import model_class.Account;
import dao.AccountDao;
import dao.AccountDaoImpl;
import view.LoginView;

public class LoginController extends Controller {

	static Account loggedInAccount = new Account();
	private LoginView loginView = new LoginView();
	AccountDao accountDao = new AccountDaoImpl();

	public void runController() {

		DatabaseController databaseController = new DatabaseController();

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
			case 2:
				databaseController.InitDatabase();
				break;
			case 3:
				databaseController.ClearDatabase();
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
		System.out.println(loggedInAccount);
		if (loggedInAccount.getId() == 0) {
			loginView.UnknownUsername();
			return;
		}

		// Checking Password (may be separate method)
		if (loginView.RequestInputPassword().equals(
				loggedInAccount.getPassword())) {
			loginView.LoginSuccesfull();

			MainController mainController = new MainController();
			mainController.runController();
		} else
			loginView.IncorrectPassword();
		// Controller.newView = true;

	}

}
