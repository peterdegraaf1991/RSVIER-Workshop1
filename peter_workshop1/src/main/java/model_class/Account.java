package model_class;

public class Account {

	private final int id;
	private String email;
	private String password;
	private int accountType;
	private int customer_id;
	
	public Account(int id, int customer_id, String email, String password, int accountType){
	   this.id = id;
	   this.email = email;
	   this.password = password;
	   this.accountType = accountType;
	   this.customer_id = customer_id;
    }

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
