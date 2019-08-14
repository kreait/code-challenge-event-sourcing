package contactsapp.boundary.internal.validation;

public class ShouldBePerson implements ValidationError{
	private String contactId;

	public ShouldBePerson(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
