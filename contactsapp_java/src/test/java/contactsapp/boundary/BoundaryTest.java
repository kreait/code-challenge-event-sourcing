package contactsapp.boundary;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.command.CreateContactList;

public class BoundaryTest {

	@Test
	public void creates_contact_list() {
		Boundary boundary = new Boundary();
		Object publishedEvent = boundary.reactTo(new CreateContactList());		
		assertTrue(publishedEvent instanceof ContactListCreated);	

		ContactList contactList = ((ContactListCreated)publishedEvent).getContactList();
		assertTrue(contactList instanceof ContactList);	
	}
}
