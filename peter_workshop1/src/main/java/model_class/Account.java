package model_class;

public class Account {

	private int id;
	private String email;
	private String password;
	private int accountType;
	private int customerId;
	
	public Account(){
	}
	
	public Account(int id, int customer_id, String email, String password, int accountType){
	   this.id = id;
	   this.email = email;
	   this.password = password;
	   this.accountType = accountType;
	   this.customerId = customerId;
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId ) {
		this.customerId = customerId ;
	}

	public int getId(){
		return id;
		}

	public String getEmail(){
		return email;
		}

	public String getPassword() {
		return password;
		}

	public int getAccountType() {
		return accountType;
		}
	
	public void setEmail(String email) {
		this.email = email;
		}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	@Override	
	public String toString() {
	  return id + " " + email + " " + accountType;
    }


}
