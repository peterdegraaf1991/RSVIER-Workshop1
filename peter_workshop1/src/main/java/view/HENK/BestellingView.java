package ws1.view;

import ws1.controller.BestellingController;
import ws1.domain.Klant;
import ws1.domain.Bestelling;

public class BestellingView extends View{

	private BestellingController orderController;
	
	public BestellingView() {
		orderController = new BestellingController();
	}
	
	@Override
	protected String createViewName() {
		return "\n*** BESTELLINGEN MENU ***";
	}

	@Override
	protected void runView() {
		int keuze = 0;
		do {
			printHeader();
			printAllOrders();
			System.out.println("Wat wilt u doen?");
			System.out.println("1: Bestelling toevoegen");
			System.out.println("2: Bestelling aanpassen");
			System.out.println("3: Bestelling verwijderen");
			System.out.println("9: Terug naar het Hoofdmenu");

			keuze = input.nextInt();
			switch (keuze) {
			case 1:
				addOrder();
				break;
			case 2:
				System.out.println("Voer het nummer in van de order die u wilt aanpassen");
				changeOrder(input.nextInt());
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
		
	public void printAllOrders() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("%-8s%-15s%-15s%-22s%-22s", "ID", "PRIJS", "KLANT ID", "STATUS", "DATUM");
		System.out.println("\n---------------------------------------------------------------------------------");
		for (Bestelling o : orderController.getAllOrders()) {
			System.out.printf("%-8s%-15s%-15s%-22s%-22s\n", o.getId(), o.getTotaalprijs(), o.getKlant().getId(),
					o.getBestelStatus(), o.getDatum());
		}
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	public void printSelectedOrder(Integer orderId) {
		Bestelling o = orderController.getOrderById(orderId);
		System.out.println("Geselecteerde order:");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("%-8s%-15s%-15s%-22s%-22s", "ID", "PRIJS", "KLANT ID", "STATUS", "DATUM");
		System.out.println("\n---------------------------------------------------------------------------------");
		if (o.getId() == null) {
			System.out.println("\nGeen order gevonden! Druk op enter om door te gaan...");
			input.nextLine();
			input.nextLine();
		} else {
			System.out.printf("%-8s%-15s%-15s%-22s%-22s\n", o.getId(), o.getTotaalprijs(), o.getKlant().getId(),
					o.getBestelStatus(), o.getDatum());
		}
	}
	
	private void addOrder() {
		
	}
	
	private void changeOrder(Integer id) {
		printSelectedOrder(id);
		
	}
	
}
