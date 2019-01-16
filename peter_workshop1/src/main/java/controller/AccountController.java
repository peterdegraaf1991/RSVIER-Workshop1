package controller;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import view.AccountView;

public class AccountController extends Controller {
	AccountView accountView = new AccountView();
	private AccountDao accountDaoImpl = new AccountDaoImpl();
	CustomerController customerController = new CustomerController();

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
				CreateAccount(customerController.ChoosePersonFromList());
				break;
			case 2:
				ChangeEmail();
				break;
			case 3:
				ChangePassword();
				break;
			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			default:
				accountView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	public void CreateAccount(Customer customer) {
		if (customer == null) {
			return;
		}
		Account account = new Account();
		account.setCustomer(customer);
		account.setEmail(accountView.RequestInputUsername());
		account.setPassword(accountView.RequestInputPassword());
		// Created accounts are always typeId 1 for now
		account.setAccountTypeId(1);
		accountDaoImpl.createAccount(account);
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
		account.setPassword(accountView.RequestInputPassword());
		accountDaoImpl.updateAccount(account);
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
	}

}
