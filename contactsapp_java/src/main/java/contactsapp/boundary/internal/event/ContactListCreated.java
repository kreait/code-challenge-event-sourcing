package contactsapp.boundary.internal.event;

import contactsapp.boundary.internal.domain.ContactList;

public class ContactListCreated {
	private String contactListId;

	public ContactListCreated(String contactListId) {
		this.contactListId = contactListId;
	}

	public ContactList getContactList() {
		return new ContactList(contactListId);
	}
}
