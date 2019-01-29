package controller;

import java.util.ArrayList;
import java.util.List;

import model_class.Account;
import model_class.Customer;
import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import dao.DaoFactory;
import dao.OrderDaoImpl;
import view.CustomerView;

public class CustomerController extends Controller {
	CustomerView customerView = new CustomerView();

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
				ViewAllCustomers();
				requestNewMenu();
				break;
			case 3:
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

	private void ViewAllCustomers() {
		if (workerOrAdminPermission() == false)
			customerView.noPermission();
		List<Customer> customerList = DaoFactory.getCustomerDao().readAllCustomers();
		if (customerList.size() <= 0) {
			customerView.NoCustomerFound();
			return;
		}
		for (int i = 0; i < customerList.size(); i++) {
			customerView.printPersonListWithoutOption(customerList);
		}
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
		ArrayList<Customer> customerList = DaoFactory.getCustomerDao()
				.readCustomersByLastname(customerView.RequestInputSurname());
		if (customerList.size() <= 0) {
			customerView.NoPersonFound();
			return null;
		}
		customerView.PrintPersonList(customerList);
		int option = customerView.ChoosePerson(customerList.size());
		Customer customer = customerList.get(option);
		return customer;
	}

	private boolean customerIdHasAccount(int customerId) {
		Account account = DaoFactory.getAccountDao().readAccountByCustomerId(customerId);
		if (account.getId() == 0)
			return false;
		else
			return true;

	}

	private void DeleteCustomer(int customerId) {
		if (DaoFactory.getOrderDao().readOrdersOfCustomerId(customerId).isEmpty() == false) {
			customerView.firstDeleteOrders();
			return;
		}
		if (customerIdHasAccount(customerId) == true)
			customerView.firstDeleteAccount();

		else
			DaoFactory.getCustomerDao().deleteCustomer(customerId);
		// Print succesfull message

	}

	private void UpdateCustomer(Customer customer) {
		customer.setFirstname(customerView.RequestInputFirstname());
		customer.setMiddlename(customerView.RequestInputMiddlename());
		customer.setSurname(customerView.RequestInputSurname());
		DaoFactory.getCustomerDao().updateCustomer(customer);
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
		if (DaoFactory.getCustomerDao().CustomerNameExists(customer) == 0)
			DaoFactory.getCustomerDao().createCustomer(customer);
		else {
			customerView.AlreadyExists();
		}
	}

	// replaced with choosepersonfromlist
	public Customer selectCustomer() {
		Customer customer = ChoosePersonFromList();
		if (customer == null) {
			System.out.println("MELDING HIER");
			return null;
		}
		return customer;
	}

}
