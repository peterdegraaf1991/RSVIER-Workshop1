package model_class;

public class Customer {

	// made public (builder normally should be private)
	public int id;
	public String firstname; 
	public String middlename; 
	public String surname;
    
	public Customer(){
	}
	
	// added public constructor
	public Customer (int id,String firstname, String middlename, String surname){
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.surname = surname;
	}
	/* might remove builder.
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
*/
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
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((middlename == null) ? 0 : middlename.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Customer other = (Customer) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (middlename == null) {
			if (other.middlename != null)
				return false;
		} else if (!middlename.equals(other.middlename))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
}
