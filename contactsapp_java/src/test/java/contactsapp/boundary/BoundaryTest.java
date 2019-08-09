package contactsapp.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddPersonToContactList;
import contactsapp.command.CreateContactList;

public class BoundaryTest {

	private static final String CONTACT_LIST_IDENTIFIER = "CONTACT_LIST_1";

	@Test
	public void creates_empty_contact_list() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreated = sendCreateContactListCommandTo(boundary, CONTACT_LIST_IDENTIFIER);
		String actualId = contactListIdIn(contactListCreated);
		assertEquals(CONTACT_LIST_IDENTIFIER, actualId);
	}

	@Test
	public void creates_contact_list_with_person() {
		Boundary boundary = new Boundary();
		ContactListCreated contactListCreatedEvent = sendCreateContactListCommandTo(boundary, CONTACT_LIST_IDENTIFIER);
		PersonAddedToContactList personAddedToContactList = sendAddPersonToContactListCommandTo(boundary, "Bertil Muth");
	}

	private ContactListCreated sendCreateContactListCommandTo(Boundary boundary, String contactListIdentifier) {
		CreateContactList commandObject = new CreateContactList(contactListIdentifier);
		ContactListCreated contactListCreatedEvent = (ContactListCreated) boundary.reactTo(commandObject);
		return contactListCreatedEvent;
	}
	
	private PersonAddedToContactList sendAddPersonToContactListCommandTo(Boundary boundary, String personName) {
		AddPersonToContactList commandObject = new AddPersonToContactList(personName, CONTACT_LIST_IDENTIFIER);
		PersonAddedToContactList personAddedToContactList = (PersonAddedToContactList) boundary
				.reactTo(commandObject);
		return personAddedToContactList;
	}

	private String contactListIdIn(Object publishedEvent) {
		ContactListCreated contactListCreated = (ContactListCreated) publishedEvent;

		ContactList contactList = contactListCreated.getContactList();
		String actualId = contactList.getId();
		return actualId;
	}
}
