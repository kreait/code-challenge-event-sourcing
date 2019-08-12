package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddPersonToContactList;

public class HandleAddPersonToContactListTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	private HandleAddPersonToContactList commandHandler;
	
	@Before
	public void setup() {
		commandHandler = new HandleAddPersonToContactList();
	}
	
	@Test
	public void adds_person_to_contact_list() {
		AddPersonToContactList command = new AddPersonToContactList(MAX_MUSTERMANN);
		PersonAddedToContactList expectedEvent = new PersonAddedToContactList(MAX_MUSTERMANN);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
	
	@Test
	public void adds_different_person_to_contact_list() {
		AddPersonToContactList command = new AddPersonToContactList(BERTIL_MUTH);
		PersonAddedToContactList expectedEvent = new PersonAddedToContactList(BERTIL_MUTH);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
}
