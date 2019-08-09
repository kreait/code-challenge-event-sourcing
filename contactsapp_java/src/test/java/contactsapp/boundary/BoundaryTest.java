package contactsapp.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;
import contactsapp.command.CreateContactList;

public class BoundaryTest {

	private static final String CONTACT_LIST_IDENTIFIER_1 = "CONTACT_LIST_1";

	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";

	@Test
	public void creates_empty_contact_list() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary, CONTACT_LIST_IDENTIFIER_1);
		String actualId = contactListIdIn(contactListCreatedEvent);
		assertEquals(CONTACT_LIST_IDENTIFIER_1, actualId);
	}

	@Test
	public void creates_contact_list_with_person() {
		Boundary boundary = new Boundary();
		createContactList(boundary, CONTACT_LIST_IDENTIFIER_1);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, MAX_MUSTERMANN,
				CONTACT_LIST_IDENTIFIER_1);
		assertEquals(MAX_MUSTERMANN, personAddedToContactList.getName());
	}

	@Test
	public void creates_contact_list_with_another_person() {
		Boundary boundary = new Boundary();
		createContactList(boundary, CONTACT_LIST_IDENTIFIER_1);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, BERTIL_MUTH,
				CONTACT_LIST_IDENTIFIER_1);
		assertEquals(BERTIL_MUTH, personAddedToContactList.getName());
	}

	@Test
	public void creates_contact_list_with_company() {
		Boundary boundary = new Boundary();
		createContactList(boundary, CONTACT_LIST_IDENTIFIER_1);
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(boundary, FOO_COM,
				CONTACT_LIST_IDENTIFIER_1);
		assertEquals(FOO_COM, companyAddedToContactList.getName());
	}
	
	@Test
	public void creates_contact_list_with_another_company() {
		Boundary boundary = new Boundary();
		createContactList(boundary, CONTACT_LIST_IDENTIFIER_1);
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(boundary, BAR_COM,
				CONTACT_LIST_IDENTIFIER_1);
		assertEquals(BAR_COM, companyAddedToContactList.getName());
	}

	private ContactListCreated createContactList(Boundary boundary, String contactListIdentifier) {
		CreateContactList commandObject = new CreateContactList(contactListIdentifier);
		ContactListCreated contactListCreatedEvent = (ContactListCreated) boundary.reactTo(commandObject);
		return contactListCreatedEvent;
	}

	private PersonAddedToContactList addPersonToContactList(Boundary boundary, String personName,
			String contactListIdentifier) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName, contactListIdentifier);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) boundary.reactTo(commandObject);
		return personAddedToContactList;
	}

	private CompanyAddedToContactList addCompanyToContactList(Boundary boundary, String companyName,
			String contactListIdentifier) { 
		AddCompanyToContactList commandObject = new AddCompanyToContactList(companyName, contactListIdentifier);
		CompanyAddedToContactList companyAddedToContactList = (CompanyAddedToContactList) boundary.reactTo(commandObject);
		return companyAddedToContactList;
	}

	private String contactListIdIn(Object publishedEvent) {
		ContactListCreated contactListCreated = (ContactListCreated) publishedEvent;

		ContactList contactList = contactListCreated.getContactList();
		String actualId = contactList.getId();
		return actualId;
	}
}
