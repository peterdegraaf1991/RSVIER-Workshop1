package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import utility.DatabaseConnection;

public class LoginView {
	private TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();
	
	public int RequestInt() {
		int option = textIO.newIntInputReader()
				.read("Choose an option from the menu");
		return option;
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
	
	public void PrintMenuHeader(){
		terminal.printf("Welkom in de winkel\n\n");
	}
	public void PrintMenuOptions(){
		terminal.println("1. Login");
		terminal.println("2. Create Account");
		terminal.println("3. Initialize Database");
		terminal.println("4. Clear Database");
		terminal.println("9. Exit Application"
				+ "\n");
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
	
	public void InvalidInput(){
		terminal.printf("Your input is invalid");
	}
	
}
