package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.EmploymentEntered;

public class HandleEmploymentEntered implements Consumer<EmploymentEntered>{
	private ContactList contactList;

	public HandleEmploymentEntered(ContactList contactList) {
		this.contactList = contactList;
	}

	@Override
	public void accept(EmploymentEntered employmentEntered) {
		String personId = employmentEntered.getPersonId();
		String companyId = employmentEntered.getCompanyId();
		String role = employmentEntered.getRole();

		contactList.enterEmployment(personId, companyId, role);
	}

	public ContactList getContactList() {
		return contactList;
	}
}
