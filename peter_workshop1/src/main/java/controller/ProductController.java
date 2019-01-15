package controller;

import view.ProductView;

public class ProductController extends Controller {

	ProductView productView = new ProductView();
	
	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				productView.ClearTerminal();
				productView.PrintMenuHeader();
				productView.PrintMenuOptions();
				Controller.newView = false;
			}
			
		keuze = productView.RequestMenuOption();
		switch (keuze) {
			case 1: 
			case 9: keuze = 0; Controller.newView = true; break;
			default:productView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}


}
