package model_class;

public class AddressType {

	private String description;
	private int id;

	
	public AddressType(int id, String description) {
		this.id = id;
		this.description = description;
	}

    public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
    	return id;
    }

    public String getDescription() {
    	return description;
    }
}
