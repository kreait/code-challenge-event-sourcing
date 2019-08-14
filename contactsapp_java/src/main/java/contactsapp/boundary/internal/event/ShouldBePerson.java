package contactsapp.boundary.internal.event;

public class ShouldBePerson {
	private String contactId;

	public ShouldBePerson(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
