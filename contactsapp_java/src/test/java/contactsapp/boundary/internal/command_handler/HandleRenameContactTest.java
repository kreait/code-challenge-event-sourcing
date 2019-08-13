package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.command.RenameContact;

public class HandleRenameContactTest {
	private static final String CONTACT_ID = "ID_1234";
	private static final String FOO_COM = "Foo.com";
	
	private HandleRenameContact commandHandler;
	
	@Before
	public void setup() {
		commandHandler = new HandleRenameContact();
	}
	
	@Test
	public void renames_con() {
		RenameContact command = new RenameContact(CONTACT_ID, FOO_COM);
		ContactRenamed expectedEvent = new ContactRenamed(CONTACT_ID, FOO_COM);

		ContactRenamed actualEvent = (ContactRenamed)commandHandler.apply(command);
		assertEquals(expectedEvent.getNewName(), actualEvent.getNewName());
	}
}
