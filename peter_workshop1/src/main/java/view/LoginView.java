package view;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class LoginView {
	private TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();
	
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
	
	public void printMessage(){
		terminal.printf("Welkom in de winkel\n\n");
	}
	public void PrintOptions(){
		terminal.printf("Enter option:");
		terminal.printf("1. Login");
		terminal.printf("2. Exit Application");
	}
	
	public void LoginHeader(){
		terminal.printf("Trying to login...");
	}
	
	public void LoginEmail() {
		terminal.printf("Enter your usename: ");
	}

	public void LoginPassword() {
		terminal.printf("Enter your password: ");
	}

	public void IncorrectPassword() {
		terminal.printf("This password is incorrect");
    }
	
	public void LoginSuccesfull() {
		terminal.printf("Login Succesfull");
	}
	
	public void UnknownUsername() {
		terminal.printf("This username doesn't exist");
	}

	public void CreateAccountHeader() {
		terminal.printf("Creating a new account: \n");
		
	}
	
}
