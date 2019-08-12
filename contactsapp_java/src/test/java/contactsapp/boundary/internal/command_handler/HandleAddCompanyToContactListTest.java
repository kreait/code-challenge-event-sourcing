package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.command.AddCompanyToContactList;

public class HandleAddCompanyToContactListTest {
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private HandleAddCompanyToContactList commandHandler;
	
	@Before
	public void setup() {
		commandHandler = new HandleAddCompanyToContactList();
	}
	
	@Test
	public void adds_company_to_contact_list() {
		AddCompanyToContactList command = new AddCompanyToContactList(FOO_COM);
		CompanyAddedToContactList expectedEvent = new CompanyAddedToContactList(FOO_COM);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		AddCompanyToContactList command = new AddCompanyToContactList(BAR_COM);
		CompanyAddedToContactList expectedEvent = new CompanyAddedToContactList(BAR_COM);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
}
