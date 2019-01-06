package model_class;

public class Account {

	private int id;
	private String email;
	private String password;
	private int accountType;
	public Customer customer;
	
	
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
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountType;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
