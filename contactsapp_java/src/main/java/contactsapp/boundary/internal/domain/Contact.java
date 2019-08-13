package contactsapp.boundary.internal.domain;

public abstract class Contact {
	private String name;
	private String id;

	protected Contact(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String newName) {
		this.name = newName;
	}
}
