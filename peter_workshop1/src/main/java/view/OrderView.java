package view;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		terminal.println("1. Add Order");
		terminal.println("2. Edit Order");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");

	}

	public void PrintEditMenuOptions() {
		terminal.println("1. Change Products");
		terminal.println("2. Change Totalcost");
		terminal.println("3. Delete");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");

	}

	public void printCustomerNamesWithOrder(String string) {
		terminal.println(string);

	}

	public void Continue() {
		char option = textIO.newCharInputReader().withInlinePossibleValues('y')
				.read("Go back to menu?");
	}

	public void printOrders(String orderToString) {
		terminal.println(orderToString);
	}

	public int chooseOrder(int size) {
		int option = textIO.newIntInputReader()
				.withMinVal(0)
				.withMaxVal(size - 1)
				.read("\nChoose the order you want to select");
		return option;
	}

	public void orderSuccesfullyDeleted() {
		terminal.println("Order Succesfully Deleted");

	}

	public BigDecimal requestTotalCost() {
		Double newTotalPrice = textIO.newDoubleInputReader().read(
				"\nEnter the new total price for this order:");
		return new BigDecimal(newTotalPrice).setScale(2, RoundingMode.HALF_UP);
	}

	public void TotalCostUpdated() {
		terminal.println("Total Price for the order has succesfully been changed");

	}

	public void noOrdersFound() {
		terminal.println("No customers with an order found");

	}

	public int chooseProductFromOrderLine(int size) {
			int option = textIO.newIntInputReader()
					.withMinVal(0)
					.withMaxVal(size - 1)
					.read("\nChoose the order you want to select");
			return option;
		}
	}

