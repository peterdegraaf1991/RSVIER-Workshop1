package view;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class View {
	public TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();

	public int RequestMenuOption() {
		int option = textIO.newIntInputReader().read(
				"Choose an option from the menu");
		return option;
	}

	public void InvalidInput() {
		terminal.println("Your input is invalid");
	}

	public void ClearTerminal() {
		terminal.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public void noPermission() {
		terminal.println("You have no permission for this action");	
	}

	public void PrintMenuOptions() {	
	}

	public void PrintMenuHeader() {
	}
	
	
	
}
