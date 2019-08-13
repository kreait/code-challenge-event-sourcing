package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class MissingContact extends TimestampedEvent{
	private String contactId;

	public MissingContact(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
