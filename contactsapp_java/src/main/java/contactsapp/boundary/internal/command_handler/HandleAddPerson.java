package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.command.AddPerson;

public class HandleAddPerson implements Function<AddPerson, Object> {
	private ContactList contactList;

	public HandleAddPerson(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public Object apply(AddPerson command) {
		String companyId = contactList.newContactId();
		PersonAdded event = new PersonAdded(companyId, command.getPersonName());
		return event;
	}
}
