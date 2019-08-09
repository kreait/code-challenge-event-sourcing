package contactsapp.boundary.internal.event;

import contactsapp.boundary.ContactList;

public class ContactListCreated {
	public ContactList getContactList() {
		return new ContactList();
	}
}
