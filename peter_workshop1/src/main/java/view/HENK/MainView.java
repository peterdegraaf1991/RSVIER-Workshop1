package ws1.view;

import ws1.controller.LoginController;
import ws1.domain.AccountType;

public class MainView extends View {
	private AccountView accountView;
	private KlantView customerView;
	private BestellingView orderView;

	@Override
	protected void runView() {
		String keuze = "0";
		do {
			printHeader();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Naar bestellingen");
			System.out.println("2: Naar eigen gegevens");
			if (LoginController.getLoggedInRole().getType().equals("Medewerker")
					|| LoginController.getLoggedInRole().getType().equals("Admin")) {
				System.out.println("3: Naar producten");
				System.out.println("4: Naar klanten");
			}
			if (LoginController.getLoggedInRole().getType().equals("Admin")) {
				System.out.println("5: Naar accounts");
			}
			System.out.println("9: Uitloggen");
			System.out.println("0: Programma afsluiten");

			keuze = input.next();
			switch (keuze) {
			case "1":
				orderView = new BestellingView();
				orderView.runView();
				break;
			case "2":
				System.out.println("Sorry, nog niet geimplementeerd");
				break;
			case "3":
				System.out.println("Sorry, nog niet geimplementeerd");
				break;
			case "4":
				if (LoginController.getLoggedInRole().getType().equals("Admin")
						|| LoginController.getLoggedInRole().getType().equals("Medewerker")) {
					customerView = new KlantView();
					customerView.runView();
				} else System.out.println("Ongeldige keuze");
				break;
			case "5":
				if (LoginController.getLoggedInRole().getType().equals("Admin")) {
					accountView = new AccountView();
					accountView.runView();
				} else System.out.println("Ongeldige keuze");
				break;
			case "9":
				LoginController.logout();
				break;
			case "0":
				System.out.println("Het programma wordt nu afgesloten");
				System.exit(0);
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (keuze != "9");
	}

	@Override
	public String createViewName() {
		return "\n *** HOOFDMENU ***";
	}
}
