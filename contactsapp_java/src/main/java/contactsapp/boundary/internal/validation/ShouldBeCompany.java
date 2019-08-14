package contactsapp.boundary.internal.validation;

public class ShouldBeCompany implements ValidationError{
	private String contactId;

	public ShouldBeCompany(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
