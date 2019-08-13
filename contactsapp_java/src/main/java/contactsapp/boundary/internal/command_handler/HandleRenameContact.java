package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.command.RenameContact;

public class HandleRenameContact implements Function<RenameContact, Object> {
	@Override
	public Object apply(RenameContact command) {
		ContactRenamed event = new ContactRenamed(command.getContactId(), command.getNewName());
		return event;
	}
}
