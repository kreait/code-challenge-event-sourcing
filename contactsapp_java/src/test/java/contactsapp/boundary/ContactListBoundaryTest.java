package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.boundary.internal.event.EmploymentEntered;
import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import contactsapp.command.EnterEmployment;
import contactsapp.command.RenameContact;
import contactsapp.query.FindContacts;
import eventstore.EventStore;

public class ContactListBoundaryTest {
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String AGILE_COACH = "Agile Coach";

	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";

	private EventStore eventStore;
	private ContactListBoundary boundary;

	@Before
	public void setup() {
		eventStore = new EventStore();
		boundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(boundary::reactToEvent);
	}
	
	@Test
	public void contact_list_is_initially_empty() {
		List<Contact> contacts = findContacts(boundary);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void adds_person() throws InterruptedException {
		Object handledEvent = addPerson(MAX_MUSTERMANN, boundary);
		assertTrue(handledEvent instanceof PersonAdded);
	}

	@Test
	public void adds_company() throws InterruptedException {
		Object handledEvent = addCompany(FOO_COM, boundary);
		assertTrue(handledEvent instanceof CompanyAdded);
	}

	@Test
	public void renames_contact() throws InterruptedException {
		CompanyAdded companyAdded = (CompanyAdded) addCompany(FOO_COM, boundary);
		String companyId = companyAdded.getCompanyId();

		renameContact(companyId, BAR_COM, boundary);
		Object handledEvent = boundary.getHandledEvent();
		assertTrue(handledEvent instanceof ContactRenamed);
	}
	
	@Test
	public void person_enters_employment() throws InterruptedException {
		PersonAdded personAdded = (PersonAdded) addPerson(MAX_MUSTERMANN, boundary);
		CompanyAdded companyAdded = (CompanyAdded) addCompany(FOO_COM, boundary);
		
		String personId = personAdded.getPersonId();
		String companyId = companyAdded.getCompanyId();

		Object handledEvent = enterEmployment(personId, companyId, AGILE_COACH, boundary);
		assertTrue(handledEvent instanceof EmploymentEntered);
	}

	@Test
	public void replays_zero_events() {
		ContactListBoundary newContactListBoundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(newContactListBoundary::reactToEvent);
		eventStore.replay();

		List<Contact> contacts = findContacts(newContactListBoundary);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void replays_two_events() {
		addPerson(MAX_MUSTERMANN, boundary);
		addCompany(BAR_COM, boundary);

		ContactListBoundary newContactListBoundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(newContactListBoundary::reactToEvent);
		eventStore.replay();

		List<Contact> newContacts = findContacts(newContactListBoundary);
		assertEquals(2, newContacts.size());
		assertEquals(MAX_MUSTERMANN, newContacts.get(0).getName());
		assertEquals(BAR_COM, newContacts.get(1).getName());
	}

	@Test
	public void replays_until_after_first_event() throws InterruptedException {
		addPerson(MAX_MUSTERMANN, boundary);

		Instant afterFirstEvent = Instant.now();
		waitNanoSecond();

		addCompany(BAR_COM, boundary);

		ContactListBoundary newContactListBoundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(newContactListBoundary::reactToEvent);
		eventStore.replayUntil(afterFirstEvent);

		List<Contact> newContacts = findContacts(newContactListBoundary);
		assertEquals(1, newContacts.size());
		assertEquals(MAX_MUSTERMANN, newContacts.get(0).getName());
	}

	private void waitNanoSecond() throws InterruptedException {
		Thread.sleep(0, 1);
	}

	private Object addPerson(String personName, ContactListBoundary boundary) {
		AddPerson command = new AddPerson(personName);
		boundary.reactToCommand(command);
		Object handledEvent = boundary.getHandledEvent();
		return handledEvent;
	}

	private Object addCompany(String companyName, ContactListBoundary boundary) {
		AddCompany command = new AddCompany(companyName);
		boundary.reactToCommand(command);
		Object handledEvent = boundary.getHandledEvent();
		return handledEvent;
	}

	private Object renameContact(String contactId, String newName, ContactListBoundary boundary) {
		RenameContact command = new RenameContact(contactId, newName);
		boundary.reactToCommand(command);
		Object handledEvent = boundary.getHandledEvent();
		return handledEvent;
	}

	private Object enterEmployment(String personId, String companyId, String role, ContactListBoundary boundary) {
		EnterEmployment command = new EnterEmployment(personId, companyId, role);
		boundary.reactToCommand(command);
		Object handledEvent = boundary.getHandledEvent();
		return handledEvent;
	}

	private List<Contact> findContacts(ContactListBoundary boundary) {
		@SuppressWarnings("unchecked")
		List<Contact> contacts = (List<Contact>) boundary.reactToQuery(new FindContacts()).get();
		return contacts;
	}
}
