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
		contactListBoundary = new ContactListBoundary(event -> {});
	}

	@Test
	public void adds_company_to_contact_list() {
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(FOO_COM, contactListBoundary);
		assertEquals(FOO_COM, companyAddedToContactList.getCompanyName());
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		CompanyAddedToContactList companyAddedToContactList = addCompanyToContactList(BAR_COM, contactListBoundary);
		assertEquals(BAR_COM, companyAddedToContactList.getCompanyName());
	}

	private CompanyAddedToContactList addCompanyToContactList(String companyName, ContactListBoundary boundary) { 
		AddCompanyToContactList commandObject = new AddCompanyToContactList(companyName);
		CompanyAddedToContactList companyAddedToContactList = (CompanyAddedToContactList) boundary.reactToCommand(commandObject).get();
		return companyAddedToContactList;
	}
}
