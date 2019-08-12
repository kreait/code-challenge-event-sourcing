package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;

public class ContactListBoundaryTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private TestEventStore testEventStore;
	private ContactListBoundary contactListBoundary;

	@Before
	public void setup() {
		testEventStore = new TestEventStore();
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
		ContactList contactList = addPerson(BERTIL_MUTH, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(BERTIL_MUTH, contacts.get(0).getName());
	}

	@Test
	public void adds_different_person_to_contact_list() {
		ContactList contactList = addPerson(MAX_MUSTERMANN, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(MAX_MUSTERMANN, contacts.get(0).getName());
	}
	
	@Test
	public void adds_company_to_contact_list() {
		ContactList contactList = addCompany(FOO_COM, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(FOO_COM, contacts.get(0).getName());
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		ContactList contactList = addCompany(BAR_COM, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
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
	
	private ContactList addPerson(String personName, ContactListBoundary boundary) { 
		AddPerson command = new AddPerson(personName);
		boundary.reactToCommand(command);
		ContactList contactList = contactListBoundary.getContactList();
		return contactList;
	}

	private ContactList addCompany(String companyName, ContactListBoundary boundary) { 
		AddCompany command = new AddCompany(companyName);
		boundary.reactToCommand(command);
		ContactList contactList = contactListBoundary.getContactList();
		return contactList;
	}
}
