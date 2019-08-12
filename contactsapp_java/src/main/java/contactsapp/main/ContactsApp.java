package contactsapp.main;

import contactsapp.boundary.ContactListBoundary;
import contactsapp.command.AddPerson;
import eventstore.EventStore;

public class ContactsApp {
	public static void main(String[] args) {
		new ContactsApp().start();
	}

	private void start() {
		ContactListBoundary contactListBoundary = drawBoundary();
		addPerson("John Q. Public", contactListBoundary);
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
}
