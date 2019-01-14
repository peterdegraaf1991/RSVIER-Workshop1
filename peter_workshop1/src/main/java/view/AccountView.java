package view;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class AccountView extends View {
	private TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();
	
	public void PrintMenuHeader() {
		terminal.println("Header AccountView \n");
	}

	public void PrintMenuOptions() {
		terminal.println("1. Create Account");
		terminal.println("2. Uninplemented...");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}
	
	public String RequestInputUsername() {
		String username = textIO.newStringInputReader()
		        .read("Enter Username");
		return username;
	}

	public String RequestInputPassword() {
		String password = textIO.newStringInputReader() 
		        .withMinLength(6)
		        .withInputMasking(true)
		        .read("Enter Password");
		return password;
	}
	
	public String RequestInputFirstname() {
		String firstname = textIO.newStringInputReader()
		        .read("Enter your firstname");
		return firstname;
	}
	
	public String RequestInputMiddlename() {
		String firstname = textIO.newStringInputReader()
		        .read("Enter your middlename");
		return firstname;
	}
	
	public String RequestInputSurname() {
		String firstname = textIO.newStringInputReader()
		        .read("Enter your surname");
		return firstname;
	}
	
}
