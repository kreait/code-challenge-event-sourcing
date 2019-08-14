package contactsapp.boundary.internal.event_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.domain.Person;
import contactsapp.boundary.internal.event.PersonAdded;

public class HandlePersonAddedTest {
	private static final String BERTIL_MUTH = "Bertil Muth";
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	
	private ContactList contactList;
	private HandlePersonAdded eventHandler;
	
	@Before
	public void setup() {
		contactList = new ContactList();
		eventHandler = new HandlePersonAdded(contactList);
	}
	
	@Test
	public void adds_person() {
		PersonAdded personAdded = new PersonAdded(BERTIL_MUTH, BERTIL_MUTH);
		eventHandler.accept(personAdded);
		
		Person person = (Person)contactList.getContact(BERTIL_MUTH).get();
		assertEquals(BERTIL_MUTH, person.getName());
	}
	
	@Test
	public void adds_two_persons_with_different_names() {
		PersonAdded maxMustermannAdded = new PersonAdded(MAX_MUSTERMANN, MAX_MUSTERMANN);
		PersonAdded bertilMuthAdded = new PersonAdded(BERTIL_MUTH, BERTIL_MUTH);

		eventHandler.accept(maxMustermannAdded);
		eventHandler.accept(bertilMuthAdded);
		
		Person bertilMuth = (Person)contactList.getContact(BERTIL_MUTH).get();
		Person maxMustermann = (Person)contactList.getContact(MAX_MUSTERMANN).get();
		
		assertEquals(BERTIL_MUTH, bertilMuth.getName());
		assertEquals(MAX_MUSTERMANN, maxMustermann.getName());
	}
	
	@Test
	public void adds_two_persons_with_the_same_name() {
		PersonAdded maxMustermann1Added = new PersonAdded(MAX_MUSTERMANN + "1", MAX_MUSTERMANN);
		PersonAdded maxMustermann2Added = new PersonAdded(MAX_MUSTERMANN + "2", MAX_MUSTERMANN);

		eventHandler.accept(maxMustermann1Added);
		eventHandler.accept(maxMustermann2Added);
		
		Person maxMustermann1 = (Person)contactList.getContact(MAX_MUSTERMANN + "1").get();
		Person maxMustermann2 = (Person)contactList.getContact(MAX_MUSTERMANN + "2").get();
		
		assertEquals(MAX_MUSTERMANN, maxMustermann1.getName());
		assertEquals(MAX_MUSTERMANN, maxMustermann2.getName());
	}
}
