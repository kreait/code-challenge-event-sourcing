package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class ContactRenamed extends TimestampedEvent{
	private String contactId;
	private String newName;

	public ContactRenamed(String contactId, String newName) {
		this.contactId = contactId;
		this.newName = newName;
	}

	public String getContactId() {
		return contactId;
	}

	public String getNewName() {
		return newName;
	}
}
