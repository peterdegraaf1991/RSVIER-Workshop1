package controller;

import view.OrderView;

public class OrderController extends Controller {

	OrderView orderView = new OrderView();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				orderView.ClearTerminal();
				orderView.PrintMenuHeader();
				orderView.PrintMenuOptions();
				Controller.newView = false;
			}
			
		keuze = orderView.RequestMenuOption();
		switch (keuze) {
			case 1: 
			case 9: keuze = 0; Controller.newView = true; break;
			default:orderView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

}
