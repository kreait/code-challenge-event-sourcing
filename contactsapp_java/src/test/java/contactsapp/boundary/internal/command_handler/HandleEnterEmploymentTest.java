package contactsapp.boundary.internal.command_handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.EmploymentEntered;
import contactsapp.boundary.internal.validation.MissingContact;
import contactsapp.boundary.internal.validation.ShouldBeCompany;
import contactsapp.boundary.internal.validation.ShouldBePerson;
import contactsapp.command.EnterEmployment;

public class HandleEnterEmploymentTest {
	private static final String BERTIL_MUTH = "Bertil Muth";
	private static final String FOO_COM = "Foo.com";
	private static final String AGILE_COACH = "Agile Coach";

	private HandleEnterEmployment commandHandler;

	private ContactList contactList;

	@Before
	public void setup() {
		contactList = new ContactList();
		commandHandler = new HandleEnterEmployment(contactList);
	}

	@Test
	public void person_enters_employment() {
		String personId = contactList.newContactId();
		String companyId = contactList.newContactId();
		contactList.addPerson(personId, BERTIL_MUTH);
		contactList.addCompany(companyId, FOO_COM);

		EnterEmployment command = new EnterEmployment(personId, companyId, AGILE_COACH);
		EmploymentEntered actualEvent = (EmploymentEntered) commandHandler.apply(command);

		assertEquals(personId, actualEvent.getPersonId());
		assertEquals(companyId, actualEvent.getCompanyId());
		assertEquals(AGILE_COACH, actualEvent.getRole());
	}

	@Test
	public void employee_isnt_a_person() {
		String companyId = contactList.newContactId();
		contactList.addCompany(companyId, FOO_COM);

		EnterEmployment command = new EnterEmployment(companyId, companyId, AGILE_COACH);
		ShouldBePerson shouldBePerson = (ShouldBePerson) commandHandler.apply(command);
		assertEquals(companyId, shouldBePerson.getContactId());
	}

	@Test
	public void company_isnt_a_company() {
		String personId = contactList.newContactId();
		contactList.addPerson(personId, BERTIL_MUTH);

		EnterEmployment command = new EnterEmployment(personId, personId, AGILE_COACH);
		ShouldBeCompany shouldBeCompany = (ShouldBeCompany) commandHandler.apply(command);
		assertEquals(personId, shouldBeCompany.getContactId());
	}

	@Test
	public void person_missing() {
		String companyId = contactList.newContactId();
		contactList.addCompany(companyId, FOO_COM);
		String invalidPersonId = "INVALID_PERSON_ID";

		EnterEmployment command = new EnterEmployment(invalidPersonId, companyId, AGILE_COACH);
		MissingContact missingContact = (MissingContact) commandHandler.apply(command);
		assertEquals(invalidPersonId, missingContact.getContactId());
	}

	@Test
	public void company_missing() {
		String personId = contactList.newContactId();
		contactList.addPerson(personId, BERTIL_MUTH);
		String invalidCompanyId = "INVALID_COMPANY_ID";

		EnterEmployment command = new EnterEmployment(personId, invalidCompanyId, AGILE_COACH);
		MissingContact missingContact = (MissingContact) commandHandler.apply(command);
		assertEquals(invalidCompanyId, missingContact.getContactId());
	}
}
