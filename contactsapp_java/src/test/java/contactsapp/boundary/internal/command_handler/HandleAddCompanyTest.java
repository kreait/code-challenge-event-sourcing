package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.command.AddCompany;

public class HandleAddCompanyTest {
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private HandleAddCompany commandHandler;
	
	@Before
	public void setup() {
		ContactList contactList = new ContactList();
		commandHandler = new HandleAddCompany(contactList);
	}
	
	@Test
	public void adds_company_to_contact_list() {
		AddCompany command = new AddCompany(FOO_COM);

		CompanyAdded actualEvent = (CompanyAdded)commandHandler.apply(command);
		assertEquals(FOO_COM, actualEvent.getCompanyName());
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		AddCompany command = new AddCompany(BAR_COM);

		CompanyAdded actualEvent = (CompanyAdded)commandHandler.apply(command);
		assertEquals(BAR_COM, actualEvent.getCompanyName());
	}
}
