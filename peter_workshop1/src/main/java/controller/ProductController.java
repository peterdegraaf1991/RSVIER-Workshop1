package controller;

import view.ProductView;

public class ProductController extends Controller {

	ProductView productView = new ProductView();
	
	@Override
	public void runController() {
		int keuze = 1;
		PrintControl.newView = true;
		do{
			if (PrintControl.newView == true){
				productView.ClearTerminal();
				productView.PrintMenuHeader();
				productView.PrintMenuOptions();
				PrintControl.newView = false;
			}
			
		keuze = productView.RequestMenuOption();
		switch (keuze) {
			case 1: 
			case 9: keuze = 0; PrintControl.newView = true; break;
			default:productView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}


}
