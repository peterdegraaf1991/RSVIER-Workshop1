package model_class;

public class AccountType {

	private int accountType;
	private String description;
	
	
	public AccountType(){
	}
	
	public AccountType(int accountType, String description){
		this.accountType = accountType;
		this.description = description;	   
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAccountType(){
		return accountType;
	}
	
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}