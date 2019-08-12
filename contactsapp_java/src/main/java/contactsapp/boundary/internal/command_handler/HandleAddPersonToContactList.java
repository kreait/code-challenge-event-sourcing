package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddPersonToContactList;

public class HandleAddPersonToContactList implements Function<AddPersonToContactList, Object> {
	@Override
	public Object apply(AddPersonToContactList command) {
		PersonAddedToContactList personAddedToContactList = new PersonAddedToContactList(command.getPersonName());
		return personAddedToContactList;
	}
}
