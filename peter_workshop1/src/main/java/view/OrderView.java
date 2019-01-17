package view;

public class OrderView extends View {

	@Override
	public void PrintMenuHeader() {
		terminal.println("Header OrderView \n");

	}

	public void PrintEditMenuHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PrintMenuOptions() {
		terminal.println("1. View Orders of Customer");
		terminal.println("2. Add Order for Customer");
		terminal.println("3. Edit Order");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");

	}

	public void PrintEditMenuOptions() {
		terminal.println("1. Change Products");
		terminal.println("2. Change Amount");
		terminal.println("4. Delete");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");

	}

	public void printCustomerNamesWithOrder(String string) {
		terminal.println(string);

	}

	public int requestAmount(int inStock) {
		int amount = textIO
				.newIntInputReader()
				.withMinVal(1)
				.withMaxVal(inStock)
				.read("How many do you wish to order? \n"
						+ "Currently in Stock:" + inStock + "\n");
		return amount;
	}

	public Boolean AddMoreProducts() {
		char option = textIO.newCharInputReader()
				.withInlinePossibleValues('y', 'n')
				.read("Do you wish to add another product to the order?");
		if (option == 'y') {
			System.out.println("Chosen option = y (true))");
			return true;
		} else
			System.out.println("Chosen option = n (false)");
		return false;
	}

	public void Continue() {
		char option = textIO.newCharInputReader().withInlinePossibleValues('y')
				.read("Go back to menu?");
	}

	public void printOrders(String orderToString) {
		terminal.println(orderToString);
	}

	public int chooseOrder(int size) {
		int option = textIO.newIntInputReader().withMinVal(0)
				.withMaxVal(size - 1)
				.read("Choose the order you want to select");
		return option;
	}

}
