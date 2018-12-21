package model_class;

public class Customer {

	private final int 	id;
	private final int 	accountId;
	private String	firstName; 
	private String	middleName; 
    private String surName;
    
	// might remove builder.
    private Customer(Builder builder){
		   this.firstName = builder.firstName;  
		   this.middleName = builder.middleName;
		   this.surName = builder.surName;
		   this.accountId = builder.accountId;
		   this.id = builder.id;
	    }
    
	public static class Builder{
		private String	firstName;
		private String 	middleName;
		private String 	surName;
		private int		accountId;
		private int		id;

        public Builder firstName(String firstName) {
          this.firstName = firstName;
          return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
          }

        public Builder surName(String surName) {
            this.surName = surName;
            return this;
          }

        public Builder accountId(int accountId) {
            this.accountId = accountId;
            return this;
          }

        public Builder id(int id){
	    	this.id = id;
	    	return this;
	    	}

	    public Customer build() {
	    	return new Customer(this);
	    }
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddlename(String middleName) {
		this.middleName = middleName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurname(String surName) {
		this.surName = surName;
	}

	public int getId() {
		return id;
	}

	public int getAccountId() {
		return accountId;
	}
	
}
