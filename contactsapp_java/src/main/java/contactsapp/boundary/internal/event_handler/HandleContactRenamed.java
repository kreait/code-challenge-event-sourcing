package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactRenamed;

public class HandleContactRenamed implements Consumer<ContactRenamed> {
	private ContactList contactList;

	public HandleContactRenamed(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public void accept(ContactRenamed event) {
		String contactId = event.getContactId();
		String newName = event.getNewName();
		contactList.renameContact(contactId, newName);
	}
}
