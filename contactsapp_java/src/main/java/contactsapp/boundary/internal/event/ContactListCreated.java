package contactsapp.boundary.internal.event;

public class ContactListCreated {
	private String contactListId;

	public ContactListCreated(String contactListId) {
		this.contactListId = contactListId;
	}

	public String getId() {
		return contactListId;
	}
}
