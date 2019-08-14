package contactsapp.main;

import java.util.List;

import contactsapp.boundary.ContactListBoundary;
import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import contactsapp.command.RenameContact;
import contactsapp.query.FindContacts;
import eventstore.EventStore;

public class ContactsApp {
	private static final String PERSON_NAME = "John Q. Public";
	private static final String NEW_PERSON_NAME = "John Q. Private";
	private static final String COMPANY_NAME = "FooBar Inc.";

	public static void main(String[] args) {
		new ContactsApp().start();
	}

	private void start() {
		EventStore eventStore = new EventStore();
		ContactListBoundary boundary = drawBoundary(eventStore);
		
		System.out.println("Adding person: " + PERSON_NAME);
		String personId = addPerson(PERSON_NAME, boundary);
		
		System.out.println("Adding company: " + COMPANY_NAME);
		addCompany(COMPANY_NAME, boundary);
		
		System.out.println("Renaming person: " + PERSON_NAME + " to: " + NEW_PERSON_NAME);
		renameContact(personId, NEW_PERSON_NAME, boundary);
		
		System.out.println("Replaying events...");
		ContactListBoundary newBoundary = drawBoundary(eventStore);
		eventStore.replay();
		
		System.out.println("\nThe contacts are:");
		List<Contact> contacts = findContacts(newBoundary);
		printToConsole(contacts);
	}

	public ContactListBoundary drawBoundary(EventStore eventStore) {
		ContactListBoundary contactListBoundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(contactListBoundary::reactToEvent);
		return contactListBoundary;
	}
	
	private String addPerson(String personName, ContactListBoundary boundary) { 
		AddPerson command = new AddPerson(personName);
		PersonAdded personAdded = (PersonAdded)boundary.reactToCommand(command).get();
		return personAdded.getPersonId();
	}
	
	private void addCompany(String companyName, ContactListBoundary boundary) {
		AddCompany command = new AddCompany(companyName);
		boundary.reactToCommand(command);
	}
	
	private void renameContact(String contactId, String newName, ContactListBoundary boundary) {
		RenameContact command = new RenameContact(contactId, newName);
		boundary.reactToCommand(command);
	}
	
	private List<Contact> findContacts(ContactListBoundary boundary) {
		@SuppressWarnings("unchecked")
		List<Contact> contacts = (List<Contact>) boundary.reactToQuery(new FindContacts()).get();
		return contacts;
	}
	
	private void printToConsole(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}
}
