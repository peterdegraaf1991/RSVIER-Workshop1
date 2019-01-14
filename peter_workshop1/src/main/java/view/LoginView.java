package view;

import java.util.concurrent.TimeUnit;

public class LoginView extends View {

	
	public void PrintMenuHeader(){
		terminal.printf("Welkom in de winkel\n\n");
	}
	public void PrintMenuOptions(){
		terminal.println("1. Login");
		terminal.println("2. Initialize Database");
		terminal.println("3. Clear Database");
		terminal.println("9. Exit Application"
				+ "\n");
	}
	
	public String RequestInputUsername() {
		String username = textIO.newStringInputReader()
				.withDefaultValue("test3@hotmail.com")
		        .read("Enter Username");
		return username;
	}

	public String RequestInputPassword() {
		String password = textIO.newStringInputReader() 
		        .withMinLength(6)
		        .withInputMasking(true)
		        .withDefaultValue("rsvier")
		        .read("Enter Password");
		return password;
	}
	
	public void IncorrectPassword() {
		terminal.printf("This password is incorrect");
    }
	
	public void LoginSuccesfull() {
		terminal.println("Login Succesfull");
		try {
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Entering MainMenu in 5");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Entering MainMenu in 4");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Entering MainMenu in 3");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Entering MainMenu in 2");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Entering MainMenu in 1");
			TimeUnit.SECONDS.sleep(1);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void UnknownUsername() {
		terminal.printf("This username doesn't exist");
	}
	
}
