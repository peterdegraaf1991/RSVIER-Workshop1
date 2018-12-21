package model_class;

public class AddressType {

	private String description;
	private int id;

	
	protected AddressType(int id, String description) {
		this.id = id;
		this.description = description;
	}

    public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int getId() {
    	return id;
    }

    private String getDescription() {
    	return description;
    }
}
