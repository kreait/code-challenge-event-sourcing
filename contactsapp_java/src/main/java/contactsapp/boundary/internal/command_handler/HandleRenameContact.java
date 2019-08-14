package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.boundary.internal.event.MissingContact;
import contactsapp.command.RenameContact;

public class HandleRenameContact implements Function<RenameContact, Object> {
	private ContactList contactList;

	public HandleRenameContact(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public Object apply(RenameContact command) {
		String contactId = command.getContactId();
		if(!contactList.isContactPresent(contactId)) {
			return new MissingContact(contactId);
		}
			
		ContactRenamed event = new ContactRenamed(contactId, command.getNewName());
		return event;
	}
}
