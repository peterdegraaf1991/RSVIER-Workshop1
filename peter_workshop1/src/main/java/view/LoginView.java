package view;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class LoginView {
	private TextIO textIO = TextIoFactory.getTextIO();
	
	public String RequestInputUsername() {
		String username = textIO.newStringInputReader()
		        .withDefaultValue("peterdegraaf1991@hotmail.com")
		        .read("1. Username \n2. Username2");
		return username;
	}

	public String RequestInputPassword() {
		String password = textIO.newStringInputReader() 
		        .withMinLength(6)
		        .withInputMasking(true)
		        .read("Password");
		return password;
	}
	
	public void printMessage(){
		System.out.println("Welkom in de winkel\n\n");
	}
	public void PrintOptions(){
		System.out.println("Enter option:");
		System.out.println("1. Login");
		System.out.println("2. Exit Application");
	}
	public void LoginEmail() {
		System.out.print("Enter your usename: ");
	}

	public void LoginPassword() {
		System.out.print("Enter your password: ");
	}

	public void IncorrectPassword() {
        System.out.println("This password is incorrect");
    }
	
	public void LoginSuccesfull() {
		System.out.println("Login Succesfull");
	}
	
	public void UnknownUsername() {
		System.out.println("This username doesn't exist");
	}
	
}
