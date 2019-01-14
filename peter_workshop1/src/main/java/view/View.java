package view;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public abstract class  View {
	public TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();
	
	public abstract void PrintMenuHeader();
	public abstract void PrintMenuOptions();
	
	
	public int RequestMenuOption() {
		int option = textIO.newIntInputReader()
				.read("Choose an option from the menu");
		return option;
	}
	
	public void InvalidInput() {
		terminal.println("Your input is invalid");
	}
	public void ClearTerminal() {
		terminal.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
