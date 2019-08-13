package contactsapp.boundary.internal.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContactList{
	private List<Contact> contacts;

	public ContactList() {
		this.contacts = new ArrayList<>();
	}
	
	public void addPerson(String personName) {
		Person person = new Person(personName);
		contacts.add(person);
	}
	
	public void addCompany(String companyName) {
		Company company = new Company(companyName);
		contacts.add(company);
	}
	
	public void renameContact(String contactId, String newName) {
		Optional<Contact> existingContact = contacts.stream()
			.filter(contact -> contact.getId().equals(contactId))
			.findFirst();
		existingContact.ifPresent(c -> c.setName(newName));
	}

	public List<Contact> getContacts() {
		return Collections.unmodifiableList(contacts);
	}
}
