package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;

public class ContactListBoundaryTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private ContactListBoundary contactListBoundary;

	@Before
	public void setup() {
		contactListBoundary = new ContactListBoundary();
	}
	
	@Test
	public void contact_list_is_empty() {
		ContactList contactList = contactListBoundary.getContactList();
		assertTrue(contactList.getContacts().isEmpty());
	}
	
	@Test
	public void adds_person_to_contact_list() {
		ContactList contactList = addPersonToContactList(BERTIL_MUTH, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(BERTIL_MUTH, contacts.get(0).getName());
	}

	@Test
	public void adds_different_person_to_contact_list() {
		ContactList contactList = addPersonToContactList(MAX_MUSTERMANN, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(MAX_MUSTERMANN, contacts.get(0).getName());
	}
	
	@Test
	public void adds_company_to_contact_list() {
		ContactList contactList = addCompanyToContactList(FOO_COM, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(FOO_COM, contacts.get(0).getName());
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		ContactList contactList = addCompanyToContactList(BAR_COM, contactListBoundary);
		List<Contact> contacts = contactList.getContacts();
		assertEquals(1, contacts.size());
		assertEquals(BAR_COM, contacts.get(0).getName());
	}
	
	private ContactList addPersonToContactList(String personName, ContactListBoundary boundary) { 
		AddPersonToContactList commandObject = new AddPersonToContactList(personName);
		boundary.reactTo(commandObject);
		ContactList contactList = contactListBoundary.getContactList();
		return contactList;
	}

	private ContactList addCompanyToContactList(String companyName, ContactListBoundary boundary) { 
		AddCompanyToContactList commandObject = new AddCompanyToContactList(companyName);
		boundary.reactTo(commandObject);
		ContactList contactList = contactListBoundary.getContactList();
		return contactList;
	}
}
