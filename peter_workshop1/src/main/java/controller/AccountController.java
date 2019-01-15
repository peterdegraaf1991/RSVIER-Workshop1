package controller;

import java.util.ArrayList;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import view.AccountView;

public class AccountController extends Controller {
AccountView accountView = new AccountView();
private AccountDao accountDaoImpl = new AccountDaoImpl();
	
@Override
public void runController() {
	int keuze = 1;
	Controller.newView = true;
	do{
		if (Controller.newView == true){
	accountView.ClearTerminal();
	accountView.PrintMenuHeader();
	accountView.PrintMenuOptions();
	Controller.newView = false;
		}
	keuze = accountView.RequestMenuOption();
	
	switch (keuze) {
		case 1: CreateAccount(); break;
		case 9: keuze = 0; Controller.newView = true; break;
		default:accountView.InvalidInput(); break;
			}
		}
	while(keuze != 0);
	}

	public void CreateAccount(){
		CustomerDao customerDaoImpl = new CustomerDaoImpl();
		ArrayList<Customer> list = customerDaoImpl.readCustomersByLastname(accountView.RequestInputSurname());
	
		int option;
		if(list.size() > 0){;
			for (int i = 0; i < list.size(); i++)
				accountView.PrintPersons(i +". " + list.get(i).toString());
				option = accountView.ChoosePerson(list.size());
		}
		else {
			accountView.NoPersonFound(); return;
		}
		Customer customer = list.get(option);
		Account account = new Account();
		account.setCustomer(customer);
		account.setEmail(accountView.RequestInputUsername());
		account.setPassword(accountView.RequestInputPassword());
		// Created accounts are always typeId 1 for now
		account.setAccountTypeId(1);
		accountDaoImpl.createAccount(account);
		Controller.newView = true;
	}
	
}

