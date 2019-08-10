package contactsapp.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
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
	public void adds_person_to_contact_list() {
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(contactListBoundary, MAX_MUSTERMANN);
		assertEquals(MAX_MUSTERMANN, personAddedToContactList.getPersonName());
	}

	@Test
	public void adds_another_person_to_contact_list() {
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(contactListBoundary, BERTIL_MUTH);
		assertEquals(BERTIL_MUTH, personAddedToContactList.getPersonName());
	}

	@Test
	public void adds_company_to_contact_list() {
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(contactListBoundary, FOO_COM);
		assertEquals(FOO_COM, companyAddedToContactList.getCompanyName());
	}
	
	@Test
	public void adds_another_company_to_contact_list() {
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(contactListBoundary, BAR_COM);
		assertEquals(BAR_COM, companyAddedToContactList.getCompanyName());
	}

	private PersonAddedToContactList addPersonToContactList(ContactListBoundary contactListBoundary, String personName) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) contactListBoundary.reactTo(commandObject);
		return personAddedToContactList;
	}

	private CompanyAddedToContactList addCompanyToContactList(ContactListBoundary boundary, String companyName) { 
		AddCompanyToContactList commandObject = new AddCompanyToContactList(companyName);
		CompanyAddedToContactList companyAddedToContactList = (CompanyAddedToContactList) boundary.reactTo(commandObject);
		return companyAddedToContactList;
	}
}
