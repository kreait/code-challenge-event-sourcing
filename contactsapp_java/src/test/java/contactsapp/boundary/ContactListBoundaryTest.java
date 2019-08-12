package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import eventstore.EventStore;

public class ContactListBoundaryTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	// Company names
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
	public void contact_list_is_empty() {
		ContactList contactList = contactListBoundary.getContactList();
		assertTrue(contactList.getContacts().isEmpty());
	}
	
	@Test
	public void adds_person_to_contact_list() {
		List<Contact> contacts = addPerson(BERTIL_MUTH, contactListBoundary);
		assertEquals(1, contacts.size());
		assertEquals(BERTIL_MUTH, contacts.get(0).getName());
	}

	@Test
	public void adds_different_person_to_contact_list() {
		List<Contact> contacts = addPerson(MAX_MUSTERMANN, contactListBoundary);
		assertEquals(1, contacts.size());
		assertEquals(MAX_MUSTERMANN, contacts.get(0).getName());
	}
	
	@Test
	public void adds_company_to_contact_list() {
		List<Contact> contacts = addCompany(FOO_COM, contactListBoundary);
		assertEquals(1, contacts.size());
		assertEquals(FOO_COM, contacts.get(0).getName());
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		List<Contact> contacts = addCompany(BAR_COM, contactListBoundary);
		assertEquals(1, contacts.size());
		assertEquals(BAR_COM, contacts.get(0).getName());
	}
	
	@Test
	public void replays_zero_events() {
		ContactListBoundary newContactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(newContactListBoundary::reactToEvent);
		testEventStore.replay();
		
		List<Contact> contacts = newContactListBoundary.getContactList().getContacts();
		assertTrue(contacts.isEmpty());
	}
	
	@Test
	public void replays_two_events() {
		addPerson(MAX_MUSTERMANN, contactListBoundary);
		addCompany(BAR_COM, contactListBoundary);
		
		ContactListBoundary newContactListBoundary = new ContactListBoundary(testEventStore);
		testEventStore.addSubscriber(newContactListBoundary::reactToEvent);
		testEventStore.replay();
		
		List<Contact> contacts = newContactListBoundary.getContactList().getContacts();
		assertEquals(2, contacts.size());
		assertEquals(MAX_MUSTERMANN, contacts.get(0).getName());
		assertEquals(BAR_COM, contacts.get(1).getName());
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
		
		List<Contact> contacts = newContactListBoundary.getContactList().getContacts();
		assertEquals(1, contacts.size());
		assertEquals(MAX_MUSTERMANN, contacts.get(0).getName());
	}

	private void waitNanoSecond() throws InterruptedException {
		Thread.sleep(0,1);
	}
	
	private List<Contact> addPerson(String personName, ContactListBoundary boundary) { 
		AddPerson command = new AddPerson(personName);
		boundary.reactToCommand(command);
		List<Contact> contacts = findContacts();
		return contacts;
	}

	private List<Contact> addCompany(String companyName, ContactListBoundary boundary) { 
		AddCompany command = new AddCompany(companyName);
		boundary.reactToCommand(command);
		List<Contact> contacts = findContacts();
		return contacts;
	}

	private List<Contact> findContacts() {
		return contactListBoundary.getContactList().getContacts();
	}
}
