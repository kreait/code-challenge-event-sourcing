package contactsapp.boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;
import contactsapp.command.CreateContactList;

public class BoundaryTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";
	
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";

	@Test
	public void creates_empty_contact_list() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary);
		String contactListId = contactListCreatedEvent.getId();
		assertNotNull(contactListId);
	}

	@Test
	public void creates_contact_list_with_person() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, MAX_MUSTERMANN,
				contactListCreatedEvent.getId());
		assertEquals(MAX_MUSTERMANN, personAddedToContactList.getName());
	}

	@Test
	public void creates_contact_list_with_another_person() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, BERTIL_MUTH,
				contactListCreatedEvent.getId());
		assertEquals(BERTIL_MUTH, personAddedToContactList.getName());
	}

	@Test
	public void creates_contact_list_with_company() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary);
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(boundary, FOO_COM,
				contactListCreatedEvent.getId());
		assertEquals(FOO_COM, companyAddedToContactList.getName());
	}
	
	@Test
	public void creates_contact_list_with_another_company() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary);
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(boundary, BAR_COM,
				contactListCreatedEvent.getId());
		assertEquals(BAR_COM, companyAddedToContactList.getName());
	}

	private ContactListCreated createContactList(Boundary boundary) {
		CreateContactList commandObject = new CreateContactList();
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
}
