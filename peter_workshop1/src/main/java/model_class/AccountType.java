package model_class;

public class AccountType {

	private int id;
	private String description;
	
	
	public AccountType(){
	}
	
	public AccountType(String description){
		this.description = description;	   
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}