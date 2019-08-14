package contactsapp.boundary.internal.command_handler;

public class ShouldBePerson {
	private String contactId;

	public ShouldBePerson(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
