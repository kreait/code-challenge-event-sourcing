package contactsapp.boundary.internal.domain;

public abstract class Contact {
	private String name;

	Contact(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
