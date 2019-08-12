package contactsapp.boundary.internal.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public List<Contact> getContacts() {
		return Collections.unmodifiableList(contacts);
	}
}
