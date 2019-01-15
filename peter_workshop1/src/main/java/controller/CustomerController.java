package controller;

import view.CustomerView;

public class CustomerController extends Controller{

	CustomerView customerView = new CustomerView();
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
			case 1: 
			case 9: keuze = 0; Controller.newView = true; break;
			default:customerView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	}
