package view;

public class OrderView extends View{

	@Override
	public void PrintMenuHeader() {
		terminal.println("Header OrderView \n");
		
	}

	@Override
	public void PrintMenuOptions() {
		terminal.println("1. Uninplemented...");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
		
	}

}
