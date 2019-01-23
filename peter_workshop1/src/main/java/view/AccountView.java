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
		terminal.println("3. Change Password of Account");
		terminal.println("4. Delete Account");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}

	public String RequestInputUsername() {
		String username = textIO.newStringInputReader().read(
				"Enter the email for this account");
		return username;
	}
	
	public String RequestInputSurname() {
		String lastname = textIO.newStringInputReader()
				.withDefaultValue("Graaf").read("Enter surname");
		return lastname;
	}

	public void PrintPersons(String personToString) {
		terminal.println(personToString);
	}

	public int ChoosePerson(int i) {
		int option = textIO.newIntInputReader().withMinVal(0).withMaxVal(i - 1)
				.read("Choose the person you want to select");
		return option;
	}

	public void NoPersonFound() {
		terminal.println("No person with that lastname found\nPlease create a person first");

	}

	public void NoAccountFound() {
		terminal.println("This person doesn't have an account yet");

	}

	public void PersonAlreadyHasAccount() {
		terminal.println("This person already has an account");
	}

	public void OnlyAdminHasPermission() {
		terminal.println("Only an admin has permission for this action");

	}

	public boolean confirmDeleteAccount() {
		Character option = textIO
				.newCharInputReader()
				.withInlinePossibleValues('y', 'n')
				.read("Are you sure you wish to delete your account? \nThis action will exit the application");
		if (option == 'y')
			return true;
		else
			return false;
	}

	public void accountCreated() {
		terminal.println("The account has been created");
	}

	public void PasswordChanged() {
		terminal.println("The password has been changed");

	}

	public void emailChanged() {
		terminal.println("The email has been changed");

	}

	public void accountDeleted() {
		terminal.println("The account has been deleted");

	}

	public int requestAccountType() {
		int option = textIO
				.newIntInputReader()
				.withInlinePossibleValues(1,2,3)
				.read("Which level of permissions do you want to give this account?\n1.Customer\n2.Worker\n3.Admin\n");
		return option;
	}

}
