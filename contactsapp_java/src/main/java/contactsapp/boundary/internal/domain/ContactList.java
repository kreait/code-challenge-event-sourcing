package contactsapp.boundary.internal.domain;

import java.util.ArrayList;
import java.util.List;

import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;

public class ContactList{
	private List<Contact> contacts;

	public ContactList() {
		this.contacts = new ArrayList<>();
	}
	
	public void addPerson(PersonAddedToContactList personAddedToContactList) {
		String personName = personAddedToContactList.getPersonName();
		Person person = new Person(personName);
		contacts.add(person);
	}
	
	public void addCompany(CompanyAddedToContactList companyAddedToContactList) {
		String companyName = companyAddedToContactList.getCompanyName();
		Company company = new Company(companyName);
		contacts.add(company);
	}

	public List<Contact> getContacts() {
		return contacts;
	}
}
