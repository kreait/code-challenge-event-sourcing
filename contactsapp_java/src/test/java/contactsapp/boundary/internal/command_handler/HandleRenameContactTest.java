package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.boundary.internal.event.MissingContact;
import contactsapp.command.RenameContact;

public class HandleRenameContactTest {
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	private static final String MISSING_CONTACT_ID = "MISSING_CONTACT_ID";

	private HandleRenameContact commandHandler;
	private ContactList contactList;

	@Before
	public void setup() {
		contactList = new ContactList();
		commandHandler = new HandleRenameContact(contactList);
	}

	@Test
	public void renames_contact() {
		String contactId = contactList.newContactId();
		contactList.addCompany(contactId, FOO_COM);

		RenameContact command = new RenameContact(contactId, BAR_COM);
		ContactRenamed expectedEvent = new ContactRenamed(contactId, BAR_COM);

		ContactRenamed actualEvent = (ContactRenamed) commandHandler.apply(command);
		assertEquals(expectedEvent.getNewName(), actualEvent.getNewName());
	}

	@Test
	public void renaming_missing_contact_fails() {
		RenameContact command = new RenameContact(MISSING_CONTACT_ID, BAR_COM);

		MissingContact actualEvent = (MissingContact) commandHandler.apply(command);
		assertEquals(MISSING_CONTACT_ID, actualEvent.getContactId());
	}
}
