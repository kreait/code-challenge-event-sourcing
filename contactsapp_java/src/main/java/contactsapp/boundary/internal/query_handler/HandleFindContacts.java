package contactsapp.boundary.internal.query_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.query.FindContacts;

public class HandleFindContacts implements Function<FindContacts, Object> {
	private ContactList contactList;

	public HandleFindContacts(ContactList contactList) {
		this.contactList = contactList;
	}

	@Override
	public Object apply(FindContacts query) {
		return contactList.getContacts();
	}

}
