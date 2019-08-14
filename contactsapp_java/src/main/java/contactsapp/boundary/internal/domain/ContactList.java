package contactsapp.boundary.internal.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ContactList {
	private List<Contact> contacts;

	public ContactList() {
		this.contacts = new ArrayList<>();
	}

	public String newContactId() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}

	public void addPerson(String id, String personName) {
		Person person = new Person(id, personName);
		contacts.add(person);
	}

	public void addCompany(String id, String companyName) {
		Company company = new Company(id, companyName);
		contacts.add(company);
	}

	public void renameContact(String contactId, String newName) {
		Optional<Contact> existingContact = getContact(contactId);
		existingContact.get().setName(newName);
	}

	public void enterEmployment(String personId, String companyId, String role) {
		Person person = (Person) getContact(personId).get();
		Company company = (Company) getContact(companyId).get();
		Employment employment = new Employment(person, company, role);
		person.setEmployment(employment);
	}

	public boolean isContactPresent(String contactId) {
		Optional<Contact> contact = getContact(contactId);
		boolean existsContact = contact.isPresent();
		return existsContact;
	}

	public Optional<Contact> getContact(String contactId) {
		Optional<Contact> contact = contacts.stream().filter(c -> c.getId().equals(contactId)).findFirst();
		return contact;
	}

	public List<Contact> getContacts() {
		return Collections.unmodifiableList(contacts);
	}
}
