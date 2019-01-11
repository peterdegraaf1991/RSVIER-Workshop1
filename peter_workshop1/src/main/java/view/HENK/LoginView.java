package ws1.view;

import ws1.controller.LoginController;
import ws1.util.Database;

public class LoginView extends View {

	private MainView mainView = new MainView();
	
	@Override
	public String createViewName() {
		return "\n*** LOGIN MENU ***";
	}

	@Override
	public void runView() {
		String keuze = "1";
		do {
			printHeader();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Inloggen");
			System.out.println("2: Testdata (her)initialiseren");
			System.out.println("0: Programma afsluiten");
	
			keuze = input.next();
			switch (keuze) {
			case "1":
				login();
				break;
			case "2":
				Database.prepareForIntegrationTest();
				System.out.println("Testdata is geinitialiseerd");
				break;
			case "0":
				System.out.println("Het programma wordt nu afgesloten");
				System.exit(0);
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (keuze != "0");
	}

	public void login() {
		System.out.println("Vul a.u.b. uw gegevens in.");
		System.out.printf("Gebruikersnaam: ");
		String username = input.next();
		System.out.printf("Wachtwoord: ");
		String wachtwoord = input.next();
		LoginController loginController = new LoginController();
		if (loginController.validateLogin(username, wachtwoord)) {
			mainView.runView();
		} else {
			System.out.println("Gebruikersnaam of wachtwoord onjuist");
		}
	}

}
