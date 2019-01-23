package controller;

import java.util.ArrayList;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import dao.OrderDaoImpl;
import view.CustomerView;

public class CustomerController extends Controller {
	CustomerView customerView = new CustomerView();
	CustomerDao customerDaoImpl = new CustomerDaoImpl();
	AccountDao accountDaoImpl = new AccountDaoImpl();
	OrderDaoImpl orderDaoImpl = new OrderDaoImpl();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				customerView.ClearTerminal();
				customerView.PrintMenuHeader();
				customerView.PrintMenuOptions();
				Controller.newView = false;
			}

			keuze = customerView.RequestMenuOption();
			switch (keuze) {
			case 1:
				CreateCustomer();
				requestNewMenu();
				break;
			case 2:
				runEditPersonMenu(ChoosePersonFromList());
				break;
			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			case 0:
				customerView.logoutTimer();
				System.exit(0);
				break;
			default:
				customerView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	public void runEditPersonMenu(Customer customer) {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				customerView.ClearTerminal();
				customerView.PrintEditMenuHeader();
				customerView.PrintEditMenuOptions();
				Controller.newView = false;
			}

			keuze = customerView.RequestMenuOption();
			switch (keuze) {
			case 1:
				UpdateCustomer(customer);
				requestNewMenu();
				break;
			case 2:
				DeleteCustomer(customer.getId());
				requestNewMenu();
				break;
			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			case 0:
				customerView.logoutTimer();
				System.exit(0);
				break;
			default:
				customerView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	public Customer ChoosePersonFromList() {
		if (workerOrAdminPermission() == false) {
			Customer customer = LoginController.loggedInCustomer;
			return customer;
		}
		CustomerDao customerDaoImpl = new CustomerDaoImpl();
		ArrayList<Customer> list = customerDaoImpl
				.readCustomersByLastname(customerView.RequestInputSurname());
		if (list.size() <= 0) {
			customerView.NoPersonFound();
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			customerView.PrintPersons(i + ". " + list.get(i).toString());
		}
		int option = customerView.ChoosePerson(list.size());
		Customer customer = list.get(option);
		return customer;
	}

	private boolean customerIdHasAccount(int customerId) {
		Account account = accountDaoImpl.readAccountByCustomerId(customerId);
		if (account.getId() == 0)
			return false;
		else
			return true;

	}

	private void DeleteCustomer(int customerId) {
		if (orderDaoImpl.readOrdersOfCustomerId(customerId).isEmpty() == false){
			customerView.firstDeleteOrders();
			return;
		}
		if (customerIdHasAccount(customerId) == true)
			customerView.firstDeleteAccount();

		else
			customerDaoImpl.deleteCustomer(customerId);
		// Print succesfull message

	}

	private void UpdateCustomer(Customer customer) {
		customer.setFirstname(customerView.RequestInputFirstname());
		customer.setMiddlename(customerView.RequestInputMiddlename());
		customer.setSurname(customerView.RequestInputSurname());
		customerDaoImpl.updateCustomer(customer);
		// Print succesfull message
	}

	private void CreateCustomer() {
		// Check if Account has permission
		if (workerOrAdminPermission() != true) {
			customerView.noPermission();
			return;
		}
		Customer customer = new Customer();
		customer.setFirstname(customerView.RequestInputFirstname());
		customer.setMiddlename(customerView.RequestInputMiddlename());
		customer.setSurname(customerView.RequestInputSurname());
		if (customerDaoImpl.CustomerNameExists(customer) == 0)
			customerDaoImpl.createCustomer(customer);
		else {
			customerView.AlreadyExists();
		}
	}

	// replaced with choosepersonfromlist
	public Customer selectCustomer() {
		Customer customer = ChoosePersonFromList();
		if (customer == null) {
			// No customer message
			return null;
		}
		return customer;
	}

}
