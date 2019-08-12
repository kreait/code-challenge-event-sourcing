package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;

public class HandlePersonAddedToContactList implements Consumer<PersonAddedToContactList> {
	private ContactList contactList;

	public HandlePersonAddedToContactList(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public void accept(PersonAddedToContactList event) {
		String personName = event.getPersonName();
		contactList.addPerson(personName);
	}

}
