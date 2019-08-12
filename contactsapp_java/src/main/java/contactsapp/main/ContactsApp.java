package contactsapp.main;

import java.util.List;

import contactsapp.boundary.ContactListBoundary;
import contactsapp.boundary.internal.domain.Contact;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import contactsapp.query.FindContacts;
import eventstore.EventStore;

public class ContactsApp {
	public static void main(String[] args) {
		new ContactsApp().start();
	}

	private void start() {
		ContactListBoundary boundary = drawBoundary();
		addPerson("John Q. Public", boundary);
		addCompany("FooBar Inc.", boundary);
		
		System.out.println("The contacts are:");
		List<Contact> contacts = findContacts(boundary);
		printToConsole(contacts);
	}

	public ContactListBoundary drawBoundary() {
		EventStore eventStore = new EventStore();
		ContactListBoundary contactListBoundary = new ContactListBoundary(eventStore);
		eventStore.addSubscriber(contactListBoundary::reactToEvent);
		return contactListBoundary;
	}
	
	private void addPerson(String personName, ContactListBoundary boundary) { 
		AddPerson command = new AddPerson(personName);
		boundary.reactToCommand(command);
	}
	
	private void addCompany(String companyName, ContactListBoundary boundary) {
		AddCompany command = new AddCompany(companyName);
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
