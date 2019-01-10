package view;

public class LoginView {

	public void printMessage(){
		System.out.println("Welkom in de winkel");
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
	
	public void UnknownEmail() {
		System.out.println("This username doesn't exist");
	}
}
