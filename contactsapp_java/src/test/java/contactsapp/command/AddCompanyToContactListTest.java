package contactsapp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.ContactListBoundary;
import contactsapp.boundary.internal.event.CompanyAddedToContactList;

public class AddCompanyToContactListTest {
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private ContactListBoundary contactListBoundary;

	@Before
	public void setup() {
		contactListBoundary = new ContactListBoundary();
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

	private CompanyAddedToContactList addCompanyToContactList(ContactListBoundary boundary, String companyName) { 
		AddCompanyToContactList commandObject = new AddCompanyToContactList(companyName);
		CompanyAddedToContactList companyAddedToContactList = (CompanyAddedToContactList) boundary.reactTo(commandObject).get();
		return companyAddedToContactList;
	}
}
