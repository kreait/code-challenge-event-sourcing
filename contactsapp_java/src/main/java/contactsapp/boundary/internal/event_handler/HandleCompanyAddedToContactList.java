package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAddedToContactList;

public class HandleCompanyAddedToContactList implements Consumer<CompanyAddedToContactList> {
	private ContactList contactList;

	public HandleCompanyAddedToContactList(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public void accept(CompanyAddedToContactList event) {
		String companyName = event.getCompanyName();
		contactList.addCompany(companyName);
	}
}
