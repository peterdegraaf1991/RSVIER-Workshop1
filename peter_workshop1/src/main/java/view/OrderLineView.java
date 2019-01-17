package view;

public class OrderLineView extends View {

	public void ProductAlreadyAdded() {
		terminal.println("This Product already has been added to this order");
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

	public int requestAmount(int inStock) {
		int amount = textIO
				.newIntInputReader()
				.withMinVal(1)
				.withMaxVal(inStock)
				.read("How many do you wish to order? \n"
						+ "Currently in Stock:" + inStock + "\n");
		return amount;
	}

	@Override
	public void PrintMenuHeader() {
	}

	@Override
	public void PrintMenuOptions() {
	}
}
