package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;

public class HandleEnterEmploymentTest {
	private static final String BERTIL_MUTH = "Bertil Muth";
	private static final String FOO_COM = "Foo.com";
	
	private HandleEnterEmployment commandHandler;

	private ContactList contactList;
	
	@Before
	public void setup() {
		contactList = new ContactList();
		commandHandler = new HandleEnterEmployment(contactList);
	}
	
	@Test
	public void person_works_for_company() {
		String personId = contactList.newContactId();
		String companyId = contactList.newContactId();
		contactList.addPerson(personId, BERTIL_MUTH);
		contactList.addCompany(companyId, FOO_COM);
		
		EnterEmployment command = new EnterEmployment(personId, companyId);
		EmploymentEntered actualEvent = (EmploymentEntered)commandHandler.apply(command);
		
		assertEquals(personId, actualEvent.getPersonId());
		assertEquals(companyId, actualEvent.getCompanyId());
	}
}
