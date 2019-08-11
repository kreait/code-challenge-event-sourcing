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
		contactListBoundary = new ContactListBoundary(event -> {});
	}

	@Test
	public void adds_person_to_contact_list() {
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(MAX_MUSTERMANN, contactListBoundary);
		assertEquals(MAX_MUSTERMANN, personAddedToContactList.getPersonName());
	}

	@Test
	public void adds_different_person_to_contact_list() {
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(BERTIL_MUTH, contactListBoundary);
		assertEquals(BERTIL_MUTH, personAddedToContactList.getPersonName());
	}

	private PersonAddedToContactList addPersonToContactList(String personName,
			ContactListBoundary contactListBoundary) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) contactListBoundary
				.reactToCommand(commandObject).get();
		return personAddedToContactList;
	}
}
