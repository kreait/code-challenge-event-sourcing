package contactsapp.boundary.internal.event;

public class ShouldBeCompany {
	private String contactId;

	public ShouldBeCompany(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}
}
