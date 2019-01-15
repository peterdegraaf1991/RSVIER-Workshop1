package controller;

import java.util.ArrayList;

import model_class.Customer;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import view.CustomerView;

public class CustomerController extends Controller{	
	CustomerView customerView = new CustomerView();
	CustomerDao customerDaoImpl = new CustomerDaoImpl();
	
	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				customerView.ClearTerminal();
				customerView.PrintMenuHeader();
				customerView.PrintMenuOptions();
				Controller.newView = false;
				}
			
		keuze = customerView.RequestMenuOption();
		switch (keuze) {
			case 1: CreateCustomer(); break;
			case 2: runEditPersonMenu(ChoosePersonFromList());break;
			case 9: keuze = 0; Controller.newView = true; break;
//			case 0:
			default:customerView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	public void runEditPersonMenu(Customer customer) {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				customerView.ClearTerminal();
				customerView.PrintEditMenuHeader();
				customerView.PrintEditMenuOptions();
				Controller.newView = false;
				}
			
		keuze = customerView.RequestMenuOption();
		switch (keuze) {
			case 1: UpdateCustomer(customer); break;
			case 2: DeleteCustomer(customer.getId()); break;
			case 9: keuze = 0; Controller.newView = true; break;
//			case 0:
			default:customerView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	public Customer ChoosePersonFromList(){
		CustomerDao customerDaoImpl = new CustomerDaoImpl();
		ArrayList<Customer> list = customerDaoImpl.readCustomersByLastname(customerView.RequestInputSurname());
			if (list.size() <= 0){
				customerView.NoPersonFound(); return null;
			}
			for (int i = 0; i < list.size(); i++){
			customerView.PrintPersons(i +". " + list.get(i).toString());
		}
		int option = customerView.ChoosePerson(list.size());
		Customer customer = list.get(option);
		return customer;		
	}
	
	private void DeleteCustomer(int id) {
		customerDaoImpl.deleteCustomer(id);
//		Print succesfull message	
	}

	private void UpdateCustomer(Customer customer) {
		customer.setFirstname(customerView.RequestInputFirstname());
		customer.setMiddlename(customerView.RequestInputMiddlename());
		customer.setSurname(customerView.RequestInputSurname());
		customerDaoImpl.updateCustomer(customer);
//		Print succesfull message	
	}

	private void CreateCustomer() {
		Customer customer = new Customer();
		customer.setFirstname(customerView.RequestInputFirstname());
		customer.setMiddlename(customerView.RequestInputMiddlename());
		customer.setSurname(customerView.RequestInputSurname());
		if (customerDaoImpl.CustomerNameExists(customer) == 0)
			customerDaoImpl.createCustomer(customer);
		else{
			customerView.AlreadyExists();
		}
	}
	}
	
