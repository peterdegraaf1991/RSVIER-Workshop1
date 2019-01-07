package model_class;

public class Account {

	private int id;
	private Customer customer;
	private String email;
	private String password;
	private int accountTypeId;
	
	
	public Account(){
	}
	
	public Account(String email, String password, int accountTypeId){
	   this.email = email;
	   this.password = password;
	   this.accountTypeId = accountTypeId;
	   this.customer = customer;
    }

	public int getId(){
		return id;
		}

	public String getEmail(){
		return email;
		}

	public int getAccountTypeId() {
		return accountTypeId;
		}
	
	public void setEmail(String email) {
		this.email = email;
		}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	@Override	
	public String toString() {
	  return id + " " + email + " " + accountTypeId;
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
