package ws1.view;

import ws1.controller.KlantController;
import ws1.dao.KlantDao;
import ws1.domain.Klant;

public class KlantView extends View {

	private KlantController klantController;

	public KlantView() {
		klantController = new KlantController();
	}

	@Override
	protected String createViewName() {
		return "\n*** KLANT MENU ***";
	}

	@Override
	protected void runView() {
		int keuze = 0;
		do {
			printHeader();
			printAllKlanten();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Klant toevoegen");
			System.out.println("2: Klant aanpassen");
			System.out.println("3: Klant verwijderen");
			System.out.println("9: Terug naar het Hoofdmenu");

			keuze = input.nextInt();
			input.nextLine();
			switch (keuze) {
			case 1:
				Klant klant = addKlant();
				AccountView accountView = new AccountView();
				accountView.addAccountForKlant(klant);
				break;
			case 2:
				Klant customer = selectKlant();
				changeKlant(customer);
				klantController.updateKlant(customer);
				break;
			case 3:
				System.out.println("Sorry, nog niet geimplementeerd");
				break;
			case 9:
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (keuze != 9);

	}

	public void printAllKlanten() {
		printKlantHeader();
		for (Klant klant : klantController.getAllKlanten()) {
			printKlantRecord(klant);
		}
		printKlantFooter();

	}
	
	public void printSelectedKlant(Klant klant) {
		System.out.println("Geselecteerde klant:");
		printKlantHeader();
		if (klant.getId() == null) {
			System.out.println("\nGeen Klant gevonden! Druk op enter om door te gaan...");
			input.nextLine();
			input.nextLine();
		} else {
			printKlantRecord(klant);
		}
		printKlantFooter();
	}

	public Klant selectKlant() {
		System.out.println("Voer het nummer in van de klant die u wilt selecteren");
		printAllKlanten();
		Klant klant = klantController.getKlantById(input.nextInt());
		return klant;
	}

	public void changeKlant(Klant klant) {
		int keuze = 0;
		do {
			printSelectedKlant(klant);
			System.out.println();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Voornaam wijzigen");
			System.out.println("2: Middelnaam wijzigen");
			System.out.println("3: Achternaam wijzigen");
			System.out.println("4: Status wijzigen");
			System.out.println("9: Opslaan");
			keuze = input.nextInt();
			input.nextLine();
			switch (keuze) {
			case 1:
				System.out.println("Geef de nieuwe voornaam:");
				klant.setVoornaam(input.nextLine());
				break;
			case 2:
				System.out.println("Geef de nieuwe middelnaam:");
				klant.setTussenvoegsel(input.nextLine());
				break;
			case 3:
				System.out.println("geef de nieuwe achternaam:");
				klant.setAchternaam(input.nextLine());
				break;
			case 4:
				klant.setActief(!klant.isActief());
				if (klant.isActief()) {
					System.out.println("Klant is nu actief");
				} else {
					System.out.println("Klant is niet meer actief");
				}
			case 9:
				break;
			default:
				System.out.println("Ongeldige keuze");
			}
		} while (keuze != 9);
	}

	public Klant addKlant() {
		Klant klant = new Klant();
		System.out.println("Wat is de voornaam van de klant?");
		klant.setVoornaam(input.nextLine());
		System.out.println("Wat is de middelnaam van de klant?");
		klant.setTussenvoegsel(input.nextLine());
		System.out.println("Wat is de achternaam van de klant?");
		klant.setAchternaam(input.nextLine());
		if (klantController.createKlant(klant)) {
			System.out.println("Klant succesvol toegevoegd!");
			return klant;
		} else {
			System.out.println("Kon klant niet toevoegen!");
			// TODO: add error handling here
			return null;
		}
		
	}
	
	private void printKlantRecord(Klant klant) {
		System.out.printf("%-8s%-22s%-22s%-22s%-8s\n", 
				klant.getId(), 
				klant.getVoornaam(), 
				(klant.getTussenvoegsel() == null) ? "" : klant.getTussenvoegsel(),
				klant.getAchternaam(), 
				(klant.isActief() ? "ja" : "nee"));
	}
	
	private void printKlantHeader() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("%-8s%-22s%-22s%-22s%-8s", "ID", "VOORNAAM", "MIDDELNAAM", "ACHTERNAAM", "ACTIEF");
		System.out.println("\n---------------------------------------------------------------------------------");
	}
	
	private void printKlantFooter() {
		System.out.println("---------------------------------------------------------------------------------");
	}

}
