package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.PersonAdded;

public class HandlePersonAdded implements Consumer<PersonAdded> {
	private ContactList contactList;

	public HandlePersonAdded(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public void accept(PersonAdded event) {
		String personId = event.getPersonId();
		String personName = event.getPersonName();
		contactList.addPerson(personId, personName);
	}

}
