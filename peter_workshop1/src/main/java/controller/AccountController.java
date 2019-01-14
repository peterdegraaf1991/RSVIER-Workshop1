package controller;

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
	do{
		if (PrintControl.newView == true){
	accountView.ClearTerminal();
	accountView.PrintMenuHeader();
	accountView.PrintMenuOptions();
		}
	keuze = accountView.RequestMenuOption();
	
	switch (keuze) {
		case 1: CreateAccount(); break;
		case 9: keuze = 0; PrintControl.newView = true; break;
		default:accountView.InvalidInput(); break;
			}
		}
	while(keuze != 0);
	}

	public void CreateAccount(){
		Customer customer = new Customer();
		customer.setFirstname(accountView.RequestInputFirstname());
		customer.setMiddlename(accountView.RequestInputMiddlename());
		customer.setSurname(accountView.RequestInputSurname());
		CustomerDao customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.createCustomer(customer);
		
		Account account = new Account();
		account.setEmail(accountView.RequestInputUsername());
		account.setPassword(accountView.RequestInputPassword());
		account.setAccountTypeId(1);
		account.setCustomer (customer);
		accountDaoImpl.createAccount(account);
	}
	
}
