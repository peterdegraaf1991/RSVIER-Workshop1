package controller;

import java.util.List;

import model_class.Account;
import model_class.Customer;
import model_class.Product;
import dao.AccountDao;
import dao.AccountDaoImpl;
import utility.Hashing;
import utility.Hashing.CannotPerformOperationException;
import view.AccountView;
import view.LoginView;

public class AccountController extends Controller {
	AccountView accountView = new AccountView();
	private AccountDao accountDaoImpl = new AccountDaoImpl();
	CustomerController customerController = new CustomerController();
	LoginView loginView = new LoginView();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				accountView.ClearTerminal();
				accountView.PrintMenuHeader();
				accountView.PrintMenuOptions();
				Controller.newView = false;
			}
			keuze = accountView.RequestMenuOption();

			switch (keuze) {
			case 1:
				CreateAccount();
				requestNewMenu();
				break;
			case 2:
				viewAllAccounts();
				requestNewMenu();
				break;
			case 3:
				ChangeEmail();
				requestNewMenu();
				break;
			case 4:
				ChangePassword();
				requestNewMenu();
				break;
			case 5:
				DeleteAccount();
				requestNewMenu();
				break;

			case 9:
				accountView.logoutTimer();
				System.exit(0);
				break;
			default:
				accountView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	private void viewAllAccounts() {
		if (workerOrAdminPermission() == false)
			accountView.noPermission();
		List<Account> list = accountDaoImpl.readAllAccounts();
		if (list.size() <= 0) {
			accountView.NoAccountFound();
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			accountView.printAccount(i + ". " + list.get(i).toString());
		}
	}
		

	private void DeleteAccount() {
		if (workerOrAdminPermission() == false) {
			if (accountView.confirmDeleteAccount() == true)
				accountDaoImpl.deleteAccount(LoginController.loggedInAccount
						.getId());
			System.exit(0);
		}
		if (workerOrAdminPermission() == true) {
			Customer customer = customerController.ChoosePersonFromList();
			Account account = accountDaoImpl.readAccountByCustomerId(customer
					.getId());
			if (account.equals(LoginController.loggedInAccount)) {
				if (accountView.confirmDeleteAccount() == true) {
					accountDaoImpl.deleteAccount(account.getId());
					System.exit(0);
				}
			} 
			else {
				accountDaoImpl.deleteAccount(account.getId());
				accountView.accountDeleted();
			}
			return;
		}
	}

	public void CreateAccount() {
		if (adminPermission() == false) {
			return;
		}
		Customer customer = customerController.ChoosePersonFromList();
		if (customer == null) {
			return;
		}
		if (accountDaoImpl.readAccountByCustomerId(customer.getId()).getId() != 0) {
			accountView.PersonAlreadyHasAccount();
			return;
		}
		Account account = new Account();
		account.setCustomer(customer);
		account.setEmail(accountView.RequestInputUsername());
		String password = loginView.RequestInputPassword();
		String hash = null;
		try {
			hash = utility.Hashing.createHash(password);
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}
		account.setHash(hash);
		int accountTypeId = accountView.requestAccountType();
		account.setAccountTypeId(accountTypeId);
		accountDaoImpl.createAccount(account);
		accountView.accountCreated();
		Controller.newView = true;
	}

	public void ChangePassword() {
		Customer customer = customerController.ChoosePersonFromList();
		if (customer == null) {
			return;
		}
		Account account = accountDaoImpl.readAccountByCustomerId(customer
				.getId());
		if (account.getId() == 0) {
			accountView.NoAccountFound();
			return;
		}
		String hash = null;
		try {
			hash = Hashing.createHash(loginView.RequestInputPassword());
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}
		account.setHash(hash);
		accountDaoImpl.updateAccount(account);
		accountView.PasswordChanged();
	}

	public void ChangeEmail() {
		Customer customer = customerController.ChoosePersonFromList();
		if (customer == null) {
			return;
		}
		Account account = accountDaoImpl.readAccountByCustomerId(customer
				.getId());
		if (account.getId() == 0) {
			accountView.NoAccountFound();
			return;
		}
		account.setEmail(accountView.RequestInputUsername());
		accountDaoImpl.updateAccount(account);
		accountView.emailChanged();
	}

}
