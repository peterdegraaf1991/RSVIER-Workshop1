package view;

public class LoginView extends View {

	public void PrintMenuHeader() {
		terminal.println(">>>>> Welkom <<<<<\n");
	}

	public void PrintMenuOptions() {
		terminal.println("1. Login");
		terminal.println("9. Exit Application" + "\n");
	}

	public String RequestInputUsername() {
		String username = textIO.newStringInputReader()
				.withDefaultValue("testaccount@email.com")
				.read("Enter Username");
		return username;
	}

	public String RequestInputPassword() {
		String password = textIO.newStringInputReader().withMinLength(6)
				.withInputMasking(true).withDefaultValue("rsvier")
				.read("Enter Password");
		return password;
	}

	public void IncorrectEmailOrPassword() {
		terminal.println("The combination of email and password is incorrect");
	}

	public void LoginSuccesfull() {
		terminal.println("Login Succesfull");
	}

	public void UnknownUsername() {
		terminal.println("This username doesn't exist");
	}

}
