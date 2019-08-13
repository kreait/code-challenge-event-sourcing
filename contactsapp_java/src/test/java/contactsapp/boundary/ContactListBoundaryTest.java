package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.boundary.internal.event.MissingContact;
import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import contactsapp.command.RenameContact;
import contactsapp.query.FindContacts;
import eventstore.EventStore;

public class ContactListBoundaryTest {
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";

	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";

	private EventStore testEventStore;
	private ContactListBoundary contactListBoundary;

	@Before
	public void setup() {
		testEventStore = new EventStore();
		contactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(contactListBoundary::reactToEvent);
	}

	@Test
	public void contact_list_is_initially_empty() {
		List<Contact> contacts = findContacts(contactListBoundary);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void adds_person() {
		PersonAdded personAdded = addPerson(BERTIL_MUTH, contactListBoundary);		
		assertEquals(BERTIL_MUTH, personAdded.getPersonName());
	}

	@Test
	public void adds_company() {
		CompanyAdded companyAdded = addCompany(FOO_COM, contactListBoundary);		
		assertEquals(FOO_COM, companyAdded.getCompanyName());
	}
	
	@Test
	public void adds_two_companies_with_the_same_name() {
		addCompany(FOO_COM, contactListBoundary);
		addCompany(FOO_COM, contactListBoundary);
		List<Contact> contacts = findContacts(contactListBoundary);

		assertEquals(2, contacts.size());
		assertEquals(FOO_COM, contacts.get(0).getName());
		assertEquals(FOO_COM, contacts.get(1).getName());
		assertFalse(contacts.get(0).getId().equals(contacts.get(1).getId()));
	}
	
	@Test
	public void renames_existing_person() {
		PersonAdded personAdded = addPerson(BERTIL_MUTH, contactListBoundary);
		String contactId = personAdded.getPersonId();
		List<Contact> newContacts = renameContact(contactId, MAX_MUSTERMANN, contactListBoundary);
		
		assertEquals(1, newContacts.size());
		assertEquals(MAX_MUSTERMANN, newContacts.get(0).getName());
	}

	@Test
	public void renames_existing_company() {
		CompanyAdded companyAdded = addCompany(BAR_COM, contactListBoundary);
		String contactId = companyAdded.getCompanyId();
		List<Contact> newContacts = renameContact(contactId, FOO_COM, contactListBoundary);
		
		assertEquals(1, newContacts.size());
		assertEquals(FOO_COM, newContacts.get(0).getName());
	}
	
	@Test
	public void renaming_non_existing_company_fails() {
		String invalidContactId = "INVALID_CONTACT_ID";
		MissingContact missingContact = renameInvalidContact(invalidContactId, BAR_COM, contactListBoundary);
		assertEquals(invalidContactId, missingContact.getContactId());
	}

	@Test
	public void replays_zero_events() {
		ContactListBoundary newContactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(newContactListBoundary::reactToEvent);
		testEventStore.replay();

		List<Contact> contacts = findContacts(newContactListBoundary);
		assertTrue(contacts.isEmpty());
	}

	@Test
	public void replays_two_events() {
		addPerson(MAX_MUSTERMANN, contactListBoundary);
		addCompany(BAR_COM, contactListBoundary);

		ContactListBoundary newContactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(newContactListBoundary::reactToEvent);
		testEventStore.replay();

		List<Contact> newContacts = findContacts(newContactListBoundary);
		assertEquals(2, newContacts.size());
		assertEquals(MAX_MUSTERMANN, newContacts.get(0).getName());
		assertEquals(BAR_COM, newContacts.get(1).getName());
	}

	@Test
	public void replays_until_after_first_event() throws InterruptedException {
		addPerson(MAX_MUSTERMANN, contactListBoundary);

		Instant afterFirstEvent = Instant.now();
		waitNanoSecond();

		addCompany(BAR_COM, contactListBoundary);

		ContactListBoundary newContactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(newContactListBoundary::reactToEvent);
		testEventStore.replayUntil(afterFirstEvent);

		List<Contact> newContacts = findContacts(newContactListBoundary);
		assertEquals(1, newContacts.size());
		assertEquals(MAX_MUSTERMANN, newContacts.get(0).getName());
	}

	private void waitNanoSecond() throws InterruptedException {
		Thread.sleep(0, 1);
	}

	private PersonAdded addPerson(String personName, ContactListBoundary boundary) {
		AddPerson command = new AddPerson(personName);
		PersonAdded personAdded = (PersonAdded)boundary.reactToCommand(command).get();
		return personAdded;
	}

	private CompanyAdded addCompany(String companyName, ContactListBoundary boundary) {
		AddCompany command = new AddCompany(companyName);
		CompanyAdded companyAdded = (CompanyAdded)boundary.reactToCommand(command).get();
		return companyAdded;
	}

	private List<Contact> renameContact(String contactId, String newName, ContactListBoundary boundary) {
		RenameContact command = new RenameContact(contactId, newName);
		boundary.reactToCommand(command);
		List<Contact> contacts = findContacts(boundary);
		return contacts;
	}
	
	private MissingContact renameInvalidContact(String contactId, String newName, ContactListBoundary boundary) {
		RenameContact command = new RenameContact(contactId, newName);
		MissingContact missingContact = (MissingContact)boundary.reactToCommand(command).get();
		return missingContact;
	}

	private List<Contact> findContacts(ContactListBoundary boundary) {
		@SuppressWarnings("unchecked")
		List<Contact> contacts = (List<Contact>) boundary.reactToQuery(new FindContacts()).get();
		return contacts;
	}
}
