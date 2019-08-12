package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.command.AddPerson;

public class HandleAddPersonTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	private HandleAddPerson commandHandler;
	
	@Before
	public void setup() {
		commandHandler = new HandleAddPerson();
	}
	
	@Test
	public void adds_person_to_contact_list() {
		AddPerson command = new AddPerson(MAX_MUSTERMANN);
		PersonAdded expectedEvent = new PersonAdded(MAX_MUSTERMANN);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
	
	@Test
	public void adds_different_person_to_contact_list() {
		AddPerson command = new AddPerson(BERTIL_MUTH);
		PersonAdded expectedEvent = new PersonAdded(BERTIL_MUTH);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
}
