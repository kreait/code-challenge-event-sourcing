package contactsapp.boundary.internal.event_handler;

import java.util.function.Consumer;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAdded;

public class HandleCompanyAdded implements Consumer<CompanyAdded> {
	private ContactList contactList;

	public HandleCompanyAdded(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public void accept(CompanyAdded event) {
		String companyName = event.getCompanyName();
		contactList.addCompany(companyName);
	}
}
