package contactsapp.boundary.internal.event_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.domain.Employment;
import contactsapp.boundary.internal.domain.Person;
import contactsapp.boundary.internal.event.EmploymentEntered;

public class HandleEmploymentEnteredTest {
	private static final String BERTIL_MUTH = "Bertil Muth";
	private static final String FOO_COM = "Foo.com";
	private static final String AGILE_COACH = "Agile Coach";
	
	private ContactList contactList;
	private HandleEmploymentEntered eventHandler;
	
	@Before
	public void setup() {
		contactList = new ContactList();
		eventHandler = new HandleEmploymentEntered(contactList);
	}
	
	@Test
	public void person_enters_company() {
		contactList.addPerson(BERTIL_MUTH, BERTIL_MUTH);
		contactList.addCompany(FOO_COM, FOO_COM);
		EmploymentEntered employmentEntered = new EmploymentEntered(BERTIL_MUTH, FOO_COM, AGILE_COACH);
		
		eventHandler.accept(employmentEntered);
		
		Person person = (Person)contactList.getContact(BERTIL_MUTH).get();
		Employment employment = person.getEmployment();
		assertEquals(BERTIL_MUTH, employment.getPerson().getId());
		assertEquals(FOO_COM, employment.getCompany().getId());
		assertEquals(AGILE_COACH, employment.getRole());
	}
}
