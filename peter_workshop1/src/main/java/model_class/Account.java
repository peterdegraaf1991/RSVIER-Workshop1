package model_class;

public class Account {

	private int id;
	private String email;
	private String password;
	private int accountType;
	private Customer customer;
	
	
	public Account(){
	}
	
	public Account(int id, Customer customer, String email, String password, int accountType){
	   this.id = id;
	   this.email = email;
	   this.password = password;
	   this.accountType = accountType;
	   this.customer = customer;
    }

	public int getId(){
		return id;
		}

	public String getEmail(){
		return email;
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
	public Customer getCustomer() {
		return customer;
	}
//getter for password allowed?
	public String getPassword() {
		return password;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
