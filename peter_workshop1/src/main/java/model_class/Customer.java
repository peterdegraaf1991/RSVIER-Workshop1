package model_class;

public class Customer {

	// made public (builder normally should be private)
	public int id;
	public String firstname; 
	public String middlename; 
	public String surname;
    
	// added public constructor
	public Customer (int id,String firstname, String middlename, String surname){
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.surname = surname;
	}
	// might remove builder.
    private Customer(Builder builder){
		   this.firstname = builder.firstname;  
		   this.middlename = builder.middlename;
		   this.surname = builder.surname;
		   this.id = builder.id;
	    }
    
	public static class Builder{
		private String	firstname;
		private String 	middlename;
		private String 	surname;
		private int		id;

        public Builder firstname(String firstname) {
          this.firstname = firstname;
          return this;
        }

        public Builder middlename(String middlename) {
            this.middlename = middlename;
            return this;
          }

        public Builder surname(String surname) {
            this.surname = surname;
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
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}
	
}
