package view;

public class CustomerView extends View {

	@Override
	public void PrintMenuHeader() {
		terminal.println("Header CustomerView \n");
	}

	public void PrintEditMenuHeader() {
		terminal.println("Header CustomerEditView \n");
	}

	@Override
	public void PrintMenuOptions() {
		terminal.println("1. Create new Customer");
		terminal.println("2. Edit Customer");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}

	public void PrintEditMenuOptions() {
		terminal.println("1. Change Name");
		terminal.println("2. Delete Customer");
		terminal.println("9. Back");
		terminal.println("0. Unimplemented");
	}

	public String RequestInputSurname() {
		String lastname = textIO.newStringInputReader()
				.withDefaultValue("Graaf").read("Enter surname of the Person");
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

	public String RequestInputFirstname() {
		String firstname = textIO.newStringInputReader()
				.read("Enter firstname");
		return firstname;
	}

	public String RequestInputMiddlename() {
		String middlename = textIO.newStringInputReader().withMinLength(0)
				.read("Enter middlename");
		return middlename;
	}

	public void AlreadyExists() {
		terminal.println("Cannot create customer: customer with that name already exists");
	}

}