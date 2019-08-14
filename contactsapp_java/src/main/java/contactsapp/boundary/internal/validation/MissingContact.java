package contactsapp.boundary.internal.validation;

public class MissingContact implements ValidationError{
	private String contactId;

	public MissingContact(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
