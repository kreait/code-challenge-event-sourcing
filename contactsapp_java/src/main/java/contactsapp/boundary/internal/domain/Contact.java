package contactsapp.boundary.internal.domain;

import java.util.UUID;

public abstract class Contact {
	private String name;
	private String id;

	protected Contact(String name) {
		this.name = name;
		this.id = generateId();
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String newName) {
		this.name = newName;
	}

	public String getId() {
		return id;
	}
	
	private String generateId() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
}
