package controller;

import view.MainView;

public class MainController extends Controller{
MainView mainView = new MainView();
	
	
	
	@Override
	public void runController() {
		int keuze = 1;
		PrintControl.newView = true;
		
		do{
			if (PrintControl.newView == true){
				mainView.ClearTerminal();
				mainView.PrintMenuHeader();
				mainView.PrintMenuOptions();
				PrintControl.newView = false;
			}
			
		keuze = mainView.RequestMenuOption();
		switch (keuze) {
			case 1: //Account Management
			AccountController accountController = new AccountController();
			accountController.runController();
			break;
			
			case 2: // Orders
			OrderController orderController = new OrderController();
			orderController.runController();
			break;
			
			case 3: // Products
			ProductController productController = new ProductController();
			productController.runController();
			break;
			
			case 4: // Customer
			CustomerController customerController = new CustomerController();
			customerController.runController();
			break;
			
			case 9: //Logout 
			keuze = 0; 
			PrintControl.newView = true; 
			mainView.logoutTimer(); 
			break;
			
			default: mainView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}


}
