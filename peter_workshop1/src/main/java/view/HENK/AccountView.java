package ws1.view;

import java.util.InputMismatchException;
import java.util.Optional;

import ws1.controller.AccountController;
import ws1.dao.AccountDao;
import ws1.domain.Account;
import ws1.domain.AccountType;
import ws1.domain.Klant;

public class AccountView extends View {

	private AccountController accountController;

	public AccountView() {
		accountController = new AccountController();
	}

	@Override
	public String createViewName() {
		return "\n*** ACCOUNT MENU ***";
	}

	@Override
	protected void runView() {
		String keuze = "0";
		do {
			printHeader();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Admin/Medewerker account toevoegen");
			System.out.println("2: Account aanpassen");
			System.out.println("3: Admin/Medewerker account verwijderen");
			System.out.println("9: Terug naar het Hoofdmenu");

			keuze = input.nextLine();
			switch (keuze) {
			case "1":
				addSingleAccount();
				break;
			case "2": {
				Optional<Account> optionalAccount = selectAccount();
				if (optionalAccount.isPresent()) {
						changeAccount(optionalAccount.get());
				} else {
					System.out.println("Account niet gevonden");
				}
				break;
			}
			case "3": {
				Optional<Account> optionalAccount = selectAccount();
				if (optionalAccount.isPresent()) {
					Account account = optionalAccount.get();
					if (!(account.getAccountType().getType().equals("Klant"))) {
						if (confirmAccountDeletion(optionalAccount.get())) {
							accountController.deleteAccount(optionalAccount.get());
							System.out.println("Account is verwijderd");
						} 
					} else {
						System.out.println("Klant account kan niet afzonderlijk worden verwijderd");
					}
				} else {
					System.out.println("Account niet gevonden");
				}
				break;
			}
			case "9":
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (!keuze.equals("9"));
	}

	private void printAllAccounts() {
		System.out.println("-----------------------------------------");
		System.out.printf("%-8s%-22s%-22s", "ID", "NAAM", "ROL");
		System.out.println("\n-----------------------------------------");
		for (Account a : accountController.getAllAccounts()) {
			System.out.printf("%-8s%-22s%-22s\n", a.getId(), a.getEmail(), a.getAccountType());
		}
		System.out.println("-----------------------------------------");
	}
	
	private void printAccount(Account account) {
		System.out.println("-----------------------------------------");
		System.out.printf("%-8s%-22s%-22s", "ID", "NAAM", "ROL");
		System.out.println("\n-----------------------------------------");
		System.out.printf("%-8s%-22s%-22s\n", account.getId(), account.getEmail(), account.getAccountType());
		System.out.println("-----------------------------------------");
	}
	
	private Optional<Account> selectAccount() {
		printAllAccounts();
		System.out.println("Voer het nummer in van het account dat u wilt selecteren:");
		try {
			Optional<Account> optionalAccount = accountController.getAccountById(input.nextInt());
			input.nextLine();
			return optionalAccount;
								
		} catch (InputMismatchException e) {
			System.out.println("Ongeldige keuze" + e.getMessage());
			return Optional.empty();
		}
	}

	public void addSingleAccount() {
		Account account  = new Account();
		System.out.println("Voer de gegevens in voor het aanmaken van een Medewerker of Admin account:");
		System.out.println("Wat is het emailadres van het account?");
		account.setEmail(input.nextLine());
		System.out.println("Wat is het wachtwoord van het account?");
		account.setWachtwoord(input.nextLine());
		account.setAccountType(selectAccountType());
		// TODO: changed because createAccount accepts account parameter now
		if (accountController.createAccount(account)) {
			System.out.println("Account succesvol toegevoegd!");
		} else {
			System.out.println("Kon account niet toevoegen!");
		}
	}

	public boolean addAccountForKlant(Klant klant) {
		Account account = new Account();
		account.setAccountType(accountController.getAccountType("Klant"));
		account.setKlant(klant);
		System.out.println("Voer de gegevens in voor het aanmaken van een account voor deze klant:");
		System.out.println("Wat is het emailadres van het account?");
		account.setEmail(input.next());
		System.out.println("Wat is het wachtwoord van het account?");
		account.setWachtwoord(input.next());
		return accountController.createAccount(account);
	}

	public void changeAccount(Account account) {
		String keuze = "0";
		do {
			printAccount(account);			
			System.out.println("Wat wilt u aanpassen?");
			System.out.println("1: Naam aanpassen");
			System.out.println("2: Wachtwoord aanpassen");
			System.out.println("3: Medewerker/Admin rol wijzigen");
			System.out.println("4: Opslaan en terug naar AccountMenu");
			System.out.println("9: Annuleren en terug naar AccountMenu");
			keuze = input.nextLine();
			switch (keuze) {
			case "1":
				changeName(account);
				break;
			case "2":
				changePassword(account);
				break;
			case "3":
				switchRole(account);
				break;
			case "4":
				accountController.updateAccount(account);
				System.out.println("Wijzigingen opgeslagen");
				keuze = "9";
				break;
			case "9":
				System.out.println("Geen wijzigingen opgeslagen");
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (!keuze.equals("9"));

	}

	private Account changeName(Account account) {
		System.out.println("Wat is het nieuwe emailadres?");
		String name = input.nextLine();
		account.setEmail(name);
		return account;
	}

	private Account changePassword(Account account) {
		System.out.println("Wat is het nieuwe wachtwoord?");
		String password = input.nextLine();
		account.setWachtwoord(password);
		return account;
	}

	private Account switchRole(Account account) {
		if (account.getAccountType().getType().equals("Klant")) {
			System.out.println("Klant rol kan niet worden aangepast!");
		} else {
			account = accountController.switchRole(account);
		}
		return account;
	}
	
	private AccountType selectAccountType() {
		String keuze = "0";
		do {
			System.out.println("Wat is de rol van het account?");
			System.out.println("1 Admin");
			System.out.println("2 Medewerker");
			
			keuze = input.nextLine();
			switch (keuze) {
			case "1": 
				return accountController.getAccountType("Admin");
			case "2":
				return accountController.getAccountType("Medewerker");
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (true);
	}
	
	private boolean confirmAccountDeletion(Account account) {
		String keuze = "0";
		do {
			printAccount(account);
			System.out.println("Weet u zeker dat u dit account wilt verwijderen?");
			System.out.println("1 Verwijderen");
			System.out.println("2 Niet verwijderen");
			
			keuze = input.nextLine();
			switch (keuze) {
			case "1": 
				return true;
			case "2":
				return false;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (true);
	}
}
