package contactsapp.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddPersonToContactList;
import contactsapp.command.CreateContactList;

public class BoundaryTest {

	private static final String CONTACT_LIST_IDENTIFIER = "CONTACT_LIST_1";
	private static final String MAX_MUSTERMANN = "Max Mustermann";
	private static final String BERTIL_MUTH = "Bertil Muth";

	@Test
	public void creates_empty_contact_list() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary, CONTACT_LIST_IDENTIFIER);
		String actualId = contactListIdIn(contactListCreatedEvent);
		assertEquals(CONTACT_LIST_IDENTIFIER, actualId);
	}

	@Test
	public void creates_contact_list_with_person() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary, CONTACT_LIST_IDENTIFIER);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, MAX_MUSTERMANN,
				CONTACT_LIST_IDENTIFIER);
		assertEquals(MAX_MUSTERMANN, personAddedToContactList.getName());
	}

	@Test
	public void creates_contact_list_with_another_person() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = createContactList(boundary, CONTACT_LIST_IDENTIFIER);
		PersonAddedToContactList personAddedToContactList = addPersonToContactList(boundary, BERTIL_MUTH,
				CONTACT_LIST_IDENTIFIER);
		assertEquals(BERTIL_MUTH, personAddedToContactList.getName());
	}

	private ContactListCreated createContactList(Boundary boundary, String contactListIdentifier) {
		CreateContactList commandObject = new CreateContactList(contactListIdentifier);
		ContactListCreated contactListCreatedEvent = (ContactListCreated) boundary.reactTo(commandObject);
		return contactListCreatedEvent;
	}

	private PersonAddedToContactList addPersonToContactList(Boundary boundary, String personName,
			String contactListIdentifier) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName, contactListIdentifier);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) boundary.reactTo(commandObject);
		return personAddedToContactList;
	}

	private String contactListIdIn(Object publishedEvent) {
		ContactListCreated contactListCreated = (ContactListCreated) publishedEvent;

		ContactList contactList = contactListCreated.getContactList();
		String actualId = contactList.getId();
		return actualId;
	}
}
