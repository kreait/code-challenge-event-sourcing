package contactsapp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import contactsapp.boundary.ContactListBoundary;
import contactsapp.boundary.internal.event.PersonAddedToContactList;

public class AddPersonToContactListTest {
	// Person names
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";

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

	private PersonAddedToContactList addPersonToContactList(ContactListBoundary contactListBoundary,
			String personName) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) contactListBoundary
				.reactTo(commandObject).get();
		return personAddedToContactList;
	}
}
