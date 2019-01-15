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
		terminal.println("2. Change Email of Account");
		terminal.println("2. Change Password of Account");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}
	
	public String RequestInputUsername() {
		String username = textIO.newStringInputReader()
		        .read("Enter the email for this account");
		return username;
	}

	public String RequestInputPassword() {
		String password = textIO.newStringInputReader() 
		        .withMinLength(6)
		        .withInputMasking(true)
		        .read("Enter the password for this account");
		return password;
	}
/*
	public String RequestInputFirstname() {
		String firstname = textIO.newStringInputReader()
		        .read("Enter firstname");
		return firstname;
	}
	
	public String RequestInputMiddlename() {
		String firstname = textIO.newStringInputReader()
		        .read("Enter middlename");
		return firstname;
	}
*/
	public String RequestInputSurname() {
		String lastname = textIO.newStringInputReader()
				.withDefaultValue("Graaf")
		        .read("Enter surname");
		return lastname;
	}
	
	public void PrintPersons(String personToString) {
		terminal.println(personToString);
	}
	
	public int ChoosePerson(int i) {
		int option = textIO.newIntInputReader()
				.withMinVal(0)
				.withMaxVal(i-1)
				.read("Choose the person you want to select");
		return option;
}

	public void NoPersonFound() {
		terminal.println("No person with that lastname found\nPlease create a person first");
		
	}
}
