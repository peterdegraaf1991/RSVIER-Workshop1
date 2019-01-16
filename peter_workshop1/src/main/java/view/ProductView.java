package view;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductView extends View{

	@Override
	public void PrintMenuHeader() {
		terminal.println("Header ProductView \n");
	}

	@Override
	public void PrintMenuOptions() {
		terminal.println("1. Show productlist");
		terminal.println("2. Add product");
		terminal.println("3. Update product");
		terminal.println("4. Delete product");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}


	public void printProduct(String ProductToString) {
			terminal.println(ProductToString);
		}

	public int RequestProductNumber(int size) {
		int index = textIO.newIntInputReader()
				.withMinVal(0)
				.withMaxVal(size)
				.read("Select Product:");
		return index;
	}

	public String RequestName() {
			String productName = textIO.newStringInputReader()
			        .read("Enter Productname:");
			return productName;
	}

	public BigDecimal RequestPrice() {
		Double productPrice = textIO.newDoubleInputReader()
		        .read("Enter ProductPrice:");
		return new BigDecimal(productPrice).setScale(2, RoundingMode.HALF_UP);
	}

	public int RequestStock() {
		int stock = textIO.newIntInputReader()
				.withMinVal(0)
				.read("Enter amount of products currently in stock");
		return stock;
	}

	public void NoProductFound() {
		terminal.println("No product(s) found in the store");
		
	}

}
