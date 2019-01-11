package ws1.view;

import java.util.Scanner;

import ws1.controller.LoginController;

public abstract class View {
	
	protected final Scanner input = new Scanner(System.in);
	protected abstract void runView();
	protected abstract String createViewName();
	
	public void printHeader() {
		String user = LoginController.getLoggedInUser();
		System.out.println("\nAangemelde gebruiker: " + ((user == null)? "Niet ingelogd" : user +
				" (" + LoginController.getLoggedInRole().getType() + ")") );
		System.out.println(createViewName());
		System.out.println();
	}

	

}
