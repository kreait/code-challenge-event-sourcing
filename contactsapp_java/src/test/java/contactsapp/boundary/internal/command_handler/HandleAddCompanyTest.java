package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.command.AddCompany;

public class HandleAddCompanyTest {
	// Company names
	private static final String FOO_COM = "Foo.com";
	private static final String BAR_COM = "Bar.com";
	
	private HandleAddCompany commandHandler;
	
	@Before
	public void setup() {
		commandHandler = new HandleAddCompany();
	}
	
	@Test
	public void adds_company_to_contact_list() {
		AddCompany command = new AddCompany(FOO_COM);
		CompanyAdded expectedEvent = new CompanyAdded(FOO_COM);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
	
	@Test
	public void adds_different_company_to_contact_list() {
		AddCompany command = new AddCompany(BAR_COM);
		CompanyAdded expectedEvent = new CompanyAdded(BAR_COM);

		Object actualEvent = commandHandler.apply(command);
		assertEquals(expectedEvent, actualEvent);
	}
}
